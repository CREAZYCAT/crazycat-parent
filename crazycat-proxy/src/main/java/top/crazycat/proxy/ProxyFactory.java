package top.crazycat.proxy;

import top.crazycat.proxy.context.TargetContext;
import top.crazycat.proxy.enums.ProxyType;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:代理工厂
 */
public interface ProxyFactory {

    <T> T getProxy(Class<T> clazz);

    <T> T getProxy(Class<T> clazz,Object bean);

    ProxyType proxyType();

    void setTargetContext(TargetContext context);

    void init();

}
