package top.crazycat.convert.impl;

import javassist.*;
import lombok.extern.slf4j.Slf4j;
import top.crazycat.convert.ConvertManager;
import top.crazycat.convert.Converter;
import top.crazycat.convert.exception.ConvertException;
import top.crazycat.convert.processor.ConvertProcessor;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/17
 * description:getset转换
 */
@Slf4j
public class DefaultConvertManager implements ConvertManager {

    private final ConcurrentMap<String, Converter> cache = new ConcurrentHashMap<>();
    private final List<String> scanPackages = new ArrayList<>();
    private List<ConvertProcessor> processorChain = new ArrayList<>();
    private volatile AtomicInteger seq = new AtomicInteger(0);
    private String packagePath;
    private boolean hasFile = true;

    public synchronized void register(Class source, Class target,String datePattern) {
        String cacheKey = getCacheKey(source, target);
        if (null == cache.get(cacheKey)) {
            cache.putIfAbsent(cacheKey, initConvert(source, target,datePattern));
        }
    }

    private String getCacheKey(Class source, Class target) {
        return source.getName() + "_" + target.getName();
    }

    @Override
    public ConvertProcessor[] processors() {
        ConvertProcessor[] p = new ConvertProcessor[processorChain.size()];
        int i = 0;
        for(ConvertProcessor s:processorChain){
            p[i] = s;
            i++;
        }
        return p;
    }

    @Override
    public void setProcessor(ConvertProcessor... processor) {
        this.processorChain.addAll(Arrays.asList(processor));
    }

    public synchronized void register(Class self) {
        register(self, self,null);
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public DefaultConvertManager() {
        this("");
    }

    public DefaultConvertManager(String packagePath) {
        this.packagePath = packagePath;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S, T> T convert(S source, Class<T> clazz) {
        try {
            Converter converter = getConverter(source.getClass(), clazz);
            assert converter != null;
            return (T) converter.convert(source);
        } catch (Exception e) {
            log.error("", e);
            throw new ConvertException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S, T> void convert(S source, T target) {
        try {
            Converter converter = getConverter(source.getClass(), target.getClass());
            assert converter != null;
            converter.convert(source,target);
        } catch (Exception e) {
            log.error("", e);
            throw new ConvertException(e);
        }
    }

    @Override
    public String[] scanPackages() {
        String[] p = new String[scanPackages.size()];
        int i = 0;
        for(String s:scanPackages){
            p[i] = s;
            i++;
        }
        return p;
    }

    @Override
    public void setScanPackages(String... param) {
        scanPackages.addAll(Arrays.asList(param));
    }

    @Override
    public void init() {
        for(ConvertProcessor processor : processorChain){
            processor.execute(this);
        }
    }

    @Override
    public void destroy() {
        cache.clear();
        if(hasFile){
            String path = ClassLoader.getSystemResource("").getPath()+(packagePath+"temp.target").replaceAll("\\.","/");
            File file = new File(path);
            if(file.isDirectory()){
                File[] files = file.listFiles();
                if(null != files && files.length>0){
                    for(File f:files){
                        f.deleteOnExit();
                    }
                }
            }
        }

    }

    private Converter getConverter(Class source, Class target) {
        String cacheKey = getCacheKey(source, target);
        Converter c = cache.get(cacheKey);
        if (null != c) {
            return c;
        }
        synchronized (this) {
            Converter converter = initConvert(source, target, null);
            cache.putIfAbsent(cacheKey, converter);
            return cache.get(cacheKey);
        }
    }

    private Converter initConvert(Class source, Class target, String datePattern) {
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass superCt = pool.get(Converter.class.getName());
            CtClass ctClass = pool.makeClass(packagePath + "temp.target.Converter$" + source.getSimpleName() + "_" + target.getSimpleName()+seq.incrementAndGet());
            ctClass.addInterface(superCt);
            CtMethod method2 = new CtMethod(
                    pool.get(void.class.getName()),
                    "convert",
                    new CtClass[]{pool.get(Object.class.getName()),pool.get(Object.class.getName())},
                    ctClass);
            method2.setBody(generatorMethod2(source, target, datePattern));
            ctClass.addMethod(method2);
            CtMethod method = new CtMethod(
                    pool.get(Object.class.getName()),
                    "convert",
                    new CtClass[]{pool.get(Object.class.getName())},
                    ctClass);
            method.setBody(generatorMethod(source, target));
            ctClass.addMethod(method);
            if(hasFile){
                ctClass.writeFile(ClassLoader.getSystemResource("").getPath());
            }
            return (Converter) ctClass.toClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generatorMethod2(Class source, Class target, String datePattern) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("try {");
        builder.append(source.getName()).append(" s = (").append(source.getName()).append(")$1;");
        builder.append(target.getName()).append(" t = (").append(target.getName()).append(")$2;");
        Method[] sf = source.getMethods();
        Method[] tf = target.getMethods();
        Map<String, Method> tfMap = new HashMap<>(tf.length);
        for (Method f : tf) {
            tfMap.put(f.getName(), f);
        }
        if(null != datePattern && datePattern.length()>0){
            builder.append(SimpleDateFormat.class.getName()).append(" sdf = new ").
                    append(SimpleDateFormat.class.getName()).append("(\"").append(datePattern).append("\");");
        }
        for (Method f : sf) {
            String name = f.getName();
            if(f.getParameterTypes().length > 0){
                continue;
            }
            if (name.startsWith("get")) {
                String tm = name.replaceFirst("get", "set");
                Method targetDeclaredMethod = tfMap.get(tm);
                if (null != targetDeclaredMethod) {
                    Class<?>[] parameterTypes = targetDeclaredMethod.getParameterTypes();
                    if(parameterTypes.length == 1){
                        if (parameterTypes[0].equals(f.getReturnType())){
                            builder.append("t.").append(tm).append("(s.").append(name).append("());");
                        }else if(null != datePattern && datePattern.length()>0){
                            if(String.class.equals(parameterTypes[0]) && Date.class.equals(f.getReturnType())){
                                builder.append("t.").append(tm).append("(").append("sdf.format(").append("s.").append(name).append("()));");
                            }else if(String.class.equals(f.getReturnType()) && Date.class.equals(parameterTypes[0])){
                                builder.append("t.").append(tm).append("(").append("sdf.parse(").append("s.").append(name).append("()));");
                            }
                        }
                    }
                }
            }
        }
        builder.append("} catch (Exception e) {" +
                "            throw new RuntimeException(e);" +
                "        }");
        builder.append("}");
        return builder.toString();
    }

    private String generatorMethod(Class source, Class target) {
        return "{" +
                "try {" +
                source.getName() + " s = (" + source.getName() + ") $1;" +
                target.getName() + " t = (" + target.getName() + ")" + target.getName() + ".class.newInstance();" +
                "convert(s,t);" +
                "return t;" +
                "} catch (Exception e) {" +
                "  throw new RuntimeException(e);" +
                "}" +
                "}";
    }
}
