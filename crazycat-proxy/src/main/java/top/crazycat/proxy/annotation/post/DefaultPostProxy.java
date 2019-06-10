package top.crazycat.proxy.annotation.post;

import lombok.extern.slf4j.Slf4j;
import top.crazycat.common.util.ListUtil;
import top.crazycat.common.util.ReflectUtil;
import top.crazycat.proxy.ProxyFactory;
import top.crazycat.proxy.ProxyManager;
import top.crazycat.proxy.annotation.Proxy;
import top.crazycat.proxy.enums.ProxyType;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/10
 * description:
 */
@Slf4j
public class DefaultPostProxy implements PostProxy {
    private static final PostProxy instance = new DefaultPostProxy();
    private DefaultPostProxy() {
    }

    public static PostProxy getInstance() {
        return instance;
    }

    @Override
    public void post(ProxyManager proxyManager) {
        String[] packages = proxyManager.scanPackages();
        if(packages.length>0){
            for(String p : packages){
                Set<Class<?>> classes = ReflectUtil.getClasses(p);
                if(ListUtil.isNotEmpty(classes)){
                    for(Class<?> clazz:classes){
                        Object target = proxyManager.targetContext().getTarget(clazz);
                        if(Objects.nonNull(target)){
                            Field[] allFields = ReflectUtil.getAllFields(target);
                            for(Field field:allFields){
                                Proxy annotation = field.getAnnotation(Proxy.class);
                                if(Objects.nonNull(annotation)){
                                    Class<?> type = field.getType();
                                    ProxyType t = annotation.value();
                                    ProxyFactory proxyFactory = proxyManager.proxyFactory(t);
                                    if(Objects.nonNull(proxyFactory)){
                                        try {
                                            Object proxy;
                                            boolean isAccessible = false;
                                            if (field.isAccessible()) {
                                                isAccessible = true;
                                            } else {
                                                field.setAccessible(true);
                                            }
                                            Object value = field.get(target);
                                            if(Objects.isNull(value)){
                                                proxy = proxyFactory.getProxy(type);
                                            }else {
                                                proxy = proxyFactory.getProxy(type,value);
                                            }
                                            field.set(target,proxy);
                                            if(!isAccessible){
                                                field.setAccessible(false);
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
