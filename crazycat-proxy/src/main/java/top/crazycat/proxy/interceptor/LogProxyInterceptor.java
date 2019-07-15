package top.crazycat.proxy.interceptor;

import com.alibaba.fastjson.JSON;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import top.crazycat.proxy.entity.InvokeEntity;
import top.crazycat.proxy.logstore.LogStore;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/7/12
 * description:
 */
public class LogProxyInterceptor implements MethodInterceptor {

    private LogStore logStore;

    public LogProxyInterceptor(LogStore logStore) {
        this.logStore = logStore;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        InvokeEntity invokeEntity = new InvokeEntity();
        invokeEntity.setClazz(o.getClass().getSuperclass().getName());
        invokeEntity.setMethod(method.getName());
        invokeEntity.setExceptionFlag(0);
        if (null != args && args.length > 0) {
            invokeEntity.setArgs(JSON.toJSONString(Arrays.asList(args)));
        }
        long start = System.currentTimeMillis();
        try {
            Object result = methodProxy.invokeSuper(o, args);
            invokeEntity.setExecuteTime(System.currentTimeMillis() - start);
            if (null != result) {
                invokeEntity.setResult(JSON.toJSONString(result));
            }
            return result;
        } catch (Throwable e) {
            invokeEntity.setExceptionFlag(1);
            invokeEntity.setExceptionMessage(e.getMessage());
            invokeEntity.setExecuteTime(System.currentTimeMillis() - start);
            throw e;
        } finally {
            logStore.record(invokeEntity);
        }
    }
}
