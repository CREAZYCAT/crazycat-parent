package top.crazycat.service;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/7/12
 * description:
 */
@Component
public class CglibProxyTest {
    public void hello(String s){
        System.out.println("hello "+s);
    }
}
