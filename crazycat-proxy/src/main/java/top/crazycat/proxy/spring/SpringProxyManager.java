package top.crazycat.proxy.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import top.crazycat.proxy.ProxyFactory;
import top.crazycat.proxy.ProxyManager;
import top.crazycat.proxy.annotation.post.DefaultPostProxy;
import top.crazycat.proxy.context.TargetContext;
import top.crazycat.proxy.enums.ProxyType;
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
    public TargetContext targetContext() {
        return targetContext;
    }

    @Override
    public ProxyFactory proxyFactory(ProxyType proxyType) {
        return factoryMap.get(proxyType);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    public ProxyManager init() {
        for (Map.Entry<ProxyType, ProxyFactory> factoryEntry : factoryMap.entrySet()) {
            factoryEntry.getValue().setTargetContext(targetContext);
        }
        DefaultPostProxy.getInstance().post(this);
        return this;
    }

}
