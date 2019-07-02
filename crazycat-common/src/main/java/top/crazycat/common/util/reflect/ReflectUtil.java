package top.crazycat.common.util.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
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
 * description:从包package中获取所有的Class
 */
public class ReflectUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

    /**
     * 从包package中获取所有的Class
     * @param pack
     * @return
     */
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
        List<File> parents = new ArrayList<>();
        while (!directs.isEmpty()) {
            File d = directs.pop();
            File[] files = d.listFiles(file -> file.isDirectory() || (file.getName().endsWith(".class")));
            for (File f : Objects.requireNonNull(files)) {
                if (f.isDirectory()) {
                    directs.push(f);
                    parents.add(f);
                    continue;
                }
                StringBuilder packageName = new StringBuilder(pack);
                File parent = f.getParentFile();
                Stack<File> stack = new Stack<>();
                while (parents.contains(parent)){
                    stack.push(parent);
                    parent = parent.getParentFile();
                }
                while (!stack.empty()){
                    parent = stack.pop();
                    packageName.append('.').append(parent.getName());
                }
                String className = f.getName().substring(0, f.getName().length() - 6);
                String path = packageName.append('.').append(className).toString();
                logger.debug("load file "+path);
                classes.add(Thread.currentThread().getContextClassLoader().loadClass(path));
            }
        }
    }


    /**
     * 从类中获取所有的字段
     * @param o
     * @return
     */
    public static Field[] getAllFields(Object o){
        return getAllFields(o.getClass());
    }

    public static Field[] getAllFields(Class c){
        List<Field> fieldList = new ArrayList<>();
        while (c!= null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(c.getDeclaredFields())));
            c= c.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

}
