package org.liang.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录控制器.
 * 
 * @author L.Yang
 * @version v1.0 2017年3月15日 下午4:05:12
 */
@Controller
public class LoginController {
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
