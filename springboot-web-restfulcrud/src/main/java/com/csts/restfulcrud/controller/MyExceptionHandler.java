package com.csts.restfulcrud.controller;

import com.csts.restfulcrud.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
//    // 1. 浏览器, 客户端返回都是json
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handleException(Exception e) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.notexist");
//        map.put("message", e.getMessage());
//        return map;
//    }

    //2. 自适应
//    @ExceptionHandler(UserNotExistException.class)
//    public String handleException(Exception e, HttpServletRequest request){
//        Map<String, Object> map = new HashMap<>();
//        // 传入错误状态码4xx 5xx, 否则不会进入定制错误页面解析流程
//        request.setAttribute("javax.servlet.error.status_code", 500);
//        map.put("code", "user.notexist");
//        map.put("message", e.getMessage());
//        return "forward:/error";
//    }

    //3. 自定义error信息:MyErrorAttributes
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        // 传入错误状态码4xx 5xx, 否则不会进入定制错误页面解析流程
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user.notexist111111");
        map.put("message", e.getMessage());

        request.setAttribute("ext", map);
        return "forward:/error";
    }
}
