package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //标注主程序类, 说明是SpringBoot应用
public class HelloWorldMainApplication {
    public static void main(String[] args) {
        //Spring应用启动
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
