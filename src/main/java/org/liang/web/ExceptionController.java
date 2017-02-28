package org.liang.web;

import org.liang.core.exception.GlobalException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws GlobalException {
        throw new GlobalException("发生错误2");
    }

}
