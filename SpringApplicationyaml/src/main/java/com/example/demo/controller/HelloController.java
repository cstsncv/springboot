package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${person.lastName}")
    private String name;

    @RequestMapping("/hello")
    public String sayHello(){
        Logger logger = LoggerFactory.getLogger(HelloController.class);
        logger.info(name);
        return "Hello " + name;
    }
}
