package top.crazycat.proxy.spring;

import lombok.extern.slf4j.Slf4j;
import top.crazycat.proxy.ProxyFactory;
import top.crazycat.proxy.ProxyManager;
import top.crazycat.proxy.annotation.post.DefaultPostProxy;
import top.crazycat.proxy.context.TargetContext;
import top.crazycat.proxy.enums.ProxyType;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
@Slf4j
public class SpringProxyManager implements ProxyManager {

    private final List<String> scanPackages = new ArrayList<>();
    private final Map<ProxyType,ProxyFactory> factoryMap = new HashMap<>();

    private TargetContext targetContext;


    @Override
    public String[] scanPackages() {
        return scanPackages.toArray(new String[0]);
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
    public TargetContext targetContext() {
        return targetContext;
    }

    @Override
    public ProxyFactory proxyFactory(ProxyType proxyType) {
        return factoryMap.get(proxyType);
    }

    @PostConstruct
    public ProxyManager init() {
        for (Map.Entry<ProxyType, ProxyFactory> factoryEntry : factoryMap.entrySet()) {
            factoryEntry.getValue().setTargetContext(targetContext);
        }
        DefaultPostProxy.getInstance().post(this);
        log.info("init spring proxy manager successfully");
        return this;
    }

}
