package top.crazycat.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
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
@ConfigurationProperties(prefix = "application")
public class ServiceConfig {
    @NestedConfigurationProperty
    private String title;
    @NestedConfigurationProperty
    private String version;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public String getVersion() {
        return version;
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/html/error/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/html/error/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/html/error/500.html");
            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }

    @Bean
    public ProxyFactory proxyFactory() {
        return new LogProxyFactory();
    }

    @Bean
    public TargetContext targetContext() {
        return new SpringContext();
    }

    @Bean
    public LogStore logStore() {
        return DefaultLogStore.getInstance();
    }


    @Bean
    public ProxyManager proxyManager(ProxyFactory proxyFactory, TargetContext targetContext) {
        return new SpringProxyManager()
                .setScanPackages("top.crazycat.service")
                .setProxyFactory(proxyFactory)
                .setTargetContext(targetContext);
    }

}
