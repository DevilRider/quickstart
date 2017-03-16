package org.liang.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * hello world cntroller Created by La on 2016/12/21.
 */
@Controller
public class HelloController {
    
    @RequestMapping("/hello")
    public String hello(ModelMap map) {
        map.addAttribute("host", "hello word");
        return "hello";
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://www.baidu.com");
        return "index";
    }

}
