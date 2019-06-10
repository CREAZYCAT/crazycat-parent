package top.crazycat.proxy;

import lombok.extern.slf4j.Slf4j;
import top.crazycat.proxy.context.TargetContext;
import top.crazycat.proxy.enums.ProxyType;
import top.crazycat.proxy.handler.LogProxyHandler;
import top.crazycat.proxy.logstore.DefaultLogStore;
import top.crazycat.proxy.logstore.LogStore;

import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created with IntelliJ IDEA.
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
@Slf4j
public class LogProxyFactory implements ProxyFactory {
    private final ConcurrentMap<Class, Object> LOG_PROXY_STORE = new ConcurrentHashMap<>();
    private TargetContext targetContext;

    @Override
    public <T> T getProxy(Class<T> clazz) {
        return getProxy(clazz, targetContext.getTarget(clazz));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getProxy(Class<T> clazz, Object bean) {
        Objects.requireNonNull(bean, "no bean input");
        T proxy = (T) LOG_PROXY_STORE.get(clazz);
        if (null == proxy) {
            synchronized (clazz.getName().intern()) {//同一接口初始化锁住,避免重复
                proxy = (T) LOG_PROXY_STORE.get(clazz);
                if (null == proxy) {
                    proxy = generatorLogProxy(clazz,bean);
                    LOG_PROXY_STORE.putIfAbsent(bean.getClass(), proxy);
                }
            }
        }
        return proxy;
    }

    @SuppressWarnings("unchecked")
    private <T> T generatorLogProxy(Class<T> clazz, Object bean) {
        LogStore logStore = targetContext.getTarget(LogStore.class);
        if(null == logStore){
            logStore = DefaultLogStore.getInstance();
        }
        if(clazz.isInterface()){
            return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class[]{clazz},
                    new LogProxyHandler(clazz,bean,logStore));
        }else {

        }
        return null;
    }


    @Override
    public ProxyType proxyType() {
        return ProxyType.LOG;
    }

    @Override
    public void setTargetContext(TargetContext context) {
        this.targetContext = context;
    }

    @Override
    public void init() {
        log.info("LogProxyFactory init successfully!");
    }
}
