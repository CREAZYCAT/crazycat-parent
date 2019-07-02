package top.crazycat.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.crazycat.proxy.LogProxyFactory;
import top.crazycat.proxy.ProxyFactory;
import top.crazycat.proxy.ProxyManager;
import top.crazycat.proxy.context.SpringContext;
import top.crazycat.proxy.context.TargetContext;
import top.crazycat.proxy.logstore.DefaultLogStore;
import top.crazycat.proxy.logstore.LogStore;
import top.crazycat.proxy.spring.SpringProxyManager;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/16
 * description:
 */
@Configuration
@ComponentScan(basePackages = {"top.crazycat.service"})
public class ServiceConfig {

    @Bean
    public ProxyFactory proxyFactory(){
        return new LogProxyFactory();
    }

    @Bean
    public TargetContext targetContext(){
        return new SpringContext();
    }

    @Bean
    public LogStore logStore(){
        return DefaultLogStore.getInstance();
    }


    @Bean
    public ProxyManager proxyManager(ProxyFactory proxyFactory, TargetContext targetContext){
        return new SpringProxyManager()
        .setScanPackages("top.crazycat.service")
        .setProxyFactory(proxyFactory)
        .setTargetContext(targetContext);
    }

}
