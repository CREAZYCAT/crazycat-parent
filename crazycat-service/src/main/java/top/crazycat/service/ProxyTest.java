package top.crazycat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.crazycat.api.DemoService;
import top.crazycat.api.entity.DemoEntity;
import top.crazycat.proxy.annotation.Proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
@Component
public class ProxyTest {

    @Proxy
    @Autowired
    private DemoService demoService;

    public void test(){
        demoService.request(new DemoEntity());
    }
}
