package top.crazycat.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
}
