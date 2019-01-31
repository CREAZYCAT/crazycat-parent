package top.crazycat.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.crazycat.api.DemoService;
import top.crazycat.api.entity.Demo2Entity;
import top.crazycat.api.entity.DemoEntity;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/16
 * description:
 */
@RestController
@RequestMapping("/")
@Slf4j
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping
    public String index(){
        log.info("hello world 你好");
        demoService.request(new DemoEntity());
        return "hello world 你好";
    }

    @RequestMapping("er")
    public void error(){
        throw new RuntimeException("诶，好像出错了");
    }

}
