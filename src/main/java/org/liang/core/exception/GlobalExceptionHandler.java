package org.liang.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e)
            throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public Error<String> jsonErrorHandler(HttpServletRequest req, GlobalException e)
            throws Exception {
        Error<String> error = new Error<>();
        error.setMessage(e.getMessage());
        error.setCode(Error.ERROR);
        error.setData("Some Data");
        error.setUrl(req.getRequestURL().toString());
        return error;
    }
}
