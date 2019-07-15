package top.crazycat.web.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.crazycat.api.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView index = new ModelAndView("index");
        index.addObject("a","a_a");
        return index;
    }

    @RequestMapping("er")
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
