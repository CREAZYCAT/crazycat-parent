//package top.crazycat.common.util;
//
//import com.alibaba.fastjson.JSON;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//
//import java.io.Serializable;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
///**
// * Created with IntelliJ IDEA.
// *
// * @author liyongbing
// * @date: 2019/6/4
// * description:远程接口代理静态工厂
// */
//public class RemoteWrapperProxyFactory implements ApplicationContextAware {
//    private static final Logger logger = LoggerFactory.getLogger(RemoteWrapperProxyFactory.class);
//    private static final ConcurrentMap<Class, Object> LOG_PROXY_STORE = new ConcurrentHashMap<>();
//    private static ApplicationContext context;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        context = applicationContext;
//        logger.info("init RemoteWrapperProxyFactory success ");
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T> T getLogProxy(final Class<T> clazz) {
//        Objects.requireNonNull(clazz, "no class input!");
//        if (!clazz.isInterface()) {
//            return context.getBean(clazz);//暂不支持class proxy
//        }
//        T proxy = (T) LOG_PROXY_STORE.get(clazz);
//        if (null == proxy) {
//            synchronized (clazz.getName().intern()) {//同一接口初始化锁住,避免重复
//                proxy = (T) LOG_PROXY_STORE.get(clazz);
//                if (null == proxy) {
//                    proxy = generatorLogProxy(clazz);
//                    LOG_PROXY_STORE.putIfAbsent(clazz, proxy);
//                }
//            }
//        }
//        return proxy;
//    }
//
//    @SuppressWarnings("unchecked")
//    private static <T> T generatorLogProxy(final Class<T> clazz) {
//        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz}, new InvocationHandler() {
//            private T target;
//
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                if (null == target) {
//                    target = context.getBean(clazz);
//                    Objects.requireNonNull(target, "no bean can find");
//                }
//                InvokeEntity invokeEntity = new InvokeEntity();
//                invokeEntity.setClazz(clazz.getName());
//                invokeEntity.setMethod(method.getName());
//                invokeEntity.setExceptionFlag(0);
//                if (null != args && args.length > 0) {
//                    invokeEntity.setArgs(JSON.toJSONString(Arrays.asList(args)));
//                }
//                long start = System.currentTimeMillis();
//                try {
//                    Object result = method.invoke(target, args);
//                    invokeEntity.setExecuteTime(System.currentTimeMillis() - start);
//                    if (null != result) {
//                        invokeEntity.setResult(JSON.toJSONString(result));
//                    }
//                    return result;
//                } catch (Throwable e) {
//                    invokeEntity.setExceptionFlag(1);
//                    invokeEntity.setExceptionMessage(e.getMessage());
//                    invokeEntity.setExecuteTime(System.currentTimeMillis() - start);
//                    throw e;
//                } finally {
//                    recordLog(invokeEntity);
//                }
//            }
//        });
//    }
//
//    private static void recordLog(InvokeEntity invokeEntity) {
//        logger.info("invoke remote : [{}]", invokeEntity.toString());
//    }
//
//    static class InvokeEntity implements Serializable {
//        private String clazz;
//        private String method;
//        private String args;
//        private String result;
//        private Long executeTime;
//        private Integer exceptionFlag;
//        private String exceptionMessage;
//
//        @Override
//        public String toString() {
//            return "InvokeEntity{" +
//                    "clazz='" + clazz + '\'' +
//                    ", method='" + method + '\'' +
//                    ", args='" + args + '\'' +
//                    ", result='" + result + '\'' +
//                    ", executeTime=" + executeTime +
//                    ", exceptionFlag=" + exceptionFlag +
//                    ", exceptionMessage='" + exceptionMessage + '\'' +
//                    '}';
//        }
//
//        public Long getExecuteTime() {
//            return executeTime;
//        }
//
//        public void setExecuteTime(Long executeTime) {
//            this.executeTime = executeTime;
//        }
//
//        public String getClazz() {
//            return clazz;
//        }
//
//        public void setClazz(String clazz) {
//            this.clazz = clazz;
//        }
//
//        public String getMethod() {
//            return method;
//        }
//
//        public void setMethod(String method) {
//            this.method = method;
//        }
//
//        public String getArgs() {
//            return args;
//        }
//
//        public void setArgs(String args) {
//            this.args = args;
//        }
//
//        public String getResult() {
//            return result;
//        }
//
//        public void setResult(String result) {
//            this.result = result;
//        }
//
//        public Integer getExceptionFlag() {
//            return exceptionFlag;
//        }
//
//        public void setExceptionFlag(Integer exceptionFlag) {
//            this.exceptionFlag = exceptionFlag;
//        }
//
//        public String getExceptionMessage() {
//            return exceptionMessage;
//        }
//
//        public void setExceptionMessage(String exceptionMessage) {
//            this.exceptionMessage = exceptionMessage;
//        }
//    }
//}
