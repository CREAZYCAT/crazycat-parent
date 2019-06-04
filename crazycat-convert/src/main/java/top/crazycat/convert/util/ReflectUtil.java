package top.crazycat.convert.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * date: 2019/1/18
 * description:从包package中获取所有的Class(指定包名路径中不能出现重复路径：package：xx.yy，path：xx.yy.xx.zz.xx.yy,会导致解析异常)
 */
public class ReflectUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

    public static Set<Class<?>> getClasses(String pack) {
        Set<Class<?>> classes = new LinkedHashSet<>();
        String packageDirName = pack.replace('.', '/');
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(pack, filePath, classes);
                } else if ("jar".equals(protocol)) {
                    JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    findAndAddClassesInPackageByJar(classes, packageDirName, jar);
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return classes;
    }

    private static void findAndAddClassesInPackageByJar(Set<Class<?>> classes, String packageDirName, JarFile jar) throws ClassNotFoundException {
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            if (name.charAt(0) == '/') {
                name = name.substring(1);
            }
            if (name.startsWith(packageDirName) && name.endsWith(".class") && !entry.isDirectory()) {
                String className = name.replace('/', '.').replace('\\', '.');
                className = className.substring(0,className.length() - 6);
                logger.debug("load jar "+className);
                classes.add(Thread.currentThread().getContextClassLoader().loadClass(className));
            }
        }
    }

    private static void findAndAddClassesInPackageByFile(String pack, String packagePath, Set<Class<?>> classes) throws ClassNotFoundException {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        Stack<File> directs = new Stack<>();
        directs.push(dir);
        while (!directs.isEmpty()) {
            File d = directs.pop();
            File[] files = d.listFiles(file -> file.isDirectory() || (file.getName().endsWith(".class")));
            for (File f : Objects.requireNonNull(files)) {
                if (f.isDirectory()) {
                    directs.push(f);
                    continue;
                }
                String path = f.getAbsolutePath().replace('/', '.').replace('\\', '.');
                path = path.substring(path.lastIndexOf(pack), path.length() - 6);
                logger.debug("load file "+path);
                classes.add(Thread.currentThread().getContextClassLoader().loadClass(path));
            }
        }
    }
}
