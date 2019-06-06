package top.crazycat.proxy.handler;

import com.alibaba.fastjson.JSON;
import top.crazycat.proxy.entity.InvokeEntity;
import top.crazycat.proxy.logstore.LogStore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
public class LogProxyHandler implements InvocationHandler {

    private Class clazz;
    private Object target;
    private LogStore logStore;

    public LogProxyHandler(Class clazz, Object target, LogStore logStore) {
        this.clazz = clazz;
        this.target = target;
        this.logStore = logStore;
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        InvokeEntity invokeEntity = new InvokeEntity();
        invokeEntity.setClazz(clazz.getName());
        invokeEntity.setMethod(method.getName());
        invokeEntity.setExceptionFlag(0);
        if (null != args && args.length > 0) {
            invokeEntity.setArgs(JSON.toJSONString(Arrays.asList(args)));
        }
        long start = System.currentTimeMillis();
        try {
            Object result = method.invoke(target, args);
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
