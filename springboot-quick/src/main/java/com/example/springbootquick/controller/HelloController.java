package com.example.springbootquick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//
//@ResponseBody  //类的所有方法返回的数据直接写给浏览器(如果对象转为json数据)
//@Controller
@RestController
public class HelloController {

//    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World quick~!";
    }


}
