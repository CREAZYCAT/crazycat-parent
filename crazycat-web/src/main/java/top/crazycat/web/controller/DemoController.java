package top.crazycat.web.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.crazycat.api.DemoService;
import top.crazycat.api.entity.Demo2Entity;
import top.crazycat.api.entity.DemoEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

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
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("GBK");
//        demoService.request(new DemoEntity());
        response.addHeader("Content-Type","text/html;charset=GBK");
        String a = request.getParameter("a");
        System.out.println(a);
        response.getWriter().write("hello world 你好呀"+ a);
//        return "hello world 你好";
    }

    @RequestMapping("error")
    public void error(){
        throw new RuntimeException("诶，好像出错了");
    }

    @RequestMapping(value = "/json",method = RequestMethod.POST,headers = "Content-Type=application/json")
    public Object json(@RequestBody JSONObject params){
        return params;
    }

    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public Object form(@RequestParam String a){
        return a;
    }

}
