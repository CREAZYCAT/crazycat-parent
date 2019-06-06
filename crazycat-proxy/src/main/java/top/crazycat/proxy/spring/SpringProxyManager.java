package top.crazycat.proxy.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import top.crazycat.common.util.ListUtil;
import top.crazycat.common.util.ReflectUtil;
import top.crazycat.proxy.ProxyFactory;
import top.crazycat.proxy.ProxyManager;
import top.crazycat.proxy.annotation.Proxy;
import top.crazycat.proxy.context.TargetContext;
import top.crazycat.proxy.enums.ProxyType;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
@Slf4j
public class SpringProxyManager implements InitializingBean,ProxyManager {

    private final List<String> scanPackages = new ArrayList<>();
    private final Map<ProxyType,ProxyFactory> factoryMap = new HashMap<>();

    private TargetContext targetContext;


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
    public ProxyManager setScanPackages(String... param) {
        scanPackages.addAll(Arrays.asList(param));
        return this;
    }

    public ProxyManager setTargetContext(TargetContext targetContext) {
        this.targetContext = targetContext;
        return this;
    }

    public ProxyManager setProxyFactory(ProxyFactory proxyFactory) {
        this.factoryMap.put(proxyFactory.proxyType(),proxyFactory);
        return this;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    public ProxyManager init() {
        for (Map.Entry<ProxyType, ProxyFactory> factoryEntry : factoryMap.entrySet()) {
            factoryEntry.getValue().setTargetContext(targetContext);
        }
        processAnnotation();
        return this;
    }

    private void processAnnotation() {
        String[] packages = scanPackages();
        if(packages.length>0){
            for(String p : packages){
                Set<Class<?>> classes = ReflectUtil.getClasses(p);
                if(ListUtil.isNotEmpty(classes)){
                    for(Class<?> clazz:classes){
                        Object target = targetContext.getTarget(clazz);
                        if(Objects.nonNull(target)){
                            Field[] allFields = ReflectUtil.getAllFields(target);
                            for(Field field:allFields){
                                Proxy annotation = field.getAnnotation(Proxy.class);
                                if(Objects.nonNull(annotation)){
                                    Class<?> type = field.getType();
                                    ProxyType t = annotation.value();
                                    ProxyFactory proxyFactory = factoryMap.get(t);
                                    if(Objects.nonNull(proxyFactory)){
                                        Object proxy = proxyFactory.getProxy(type);
                                        try {
                                            if(!field.isAccessible()){
                                                field.setAccessible(true);
                                                field.set(target,proxy);
                                                field.setAccessible(false);
                                            }else {
                                                field.set(target,proxy);
                                            }
                                        } catch (IllegalAccessException e) {
                                            log.error("IllegalAccessException",e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
