package top.crazycat.proxy;

import top.crazycat.proxy.context.TargetContext;
import top.crazycat.proxy.enums.ProxyType;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:代理初始化管理
 */
public interface ProxyManager {

    String[] scanPackages();

    ProxyManager setScanPackages(String... param);

    ProxyManager setTargetContext(TargetContext targetContext);

    ProxyManager setProxyFactory(ProxyFactory proxyFactory);

    TargetContext targetContext();

    ProxyFactory proxyFactory(ProxyType proxyType);

    ProxyManager init();


}
