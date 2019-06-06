package top.crazycat.proxy;

import top.crazycat.proxy.context.TargetContext;
import top.crazycat.proxy.spring.SpringProxyManager;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
public interface ProxyManager {

    String[] scanPackages();

    ProxyManager setScanPackages(String... param);

    ProxyManager setTargetContext(TargetContext targetContext);

    ProxyManager setProxyFactory(ProxyFactory proxyFactory);

    ProxyManager init();


}
