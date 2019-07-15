package top.crazycat.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.crazycat.service.ProxyTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/16
 * description:main
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext app =SpringApplication.run(Application.class, args);
        ProxyTest t = app.getBean(ProxyTest.class);
        t.test();
    }
}
