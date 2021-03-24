package com.example.demo.config;

import com.example.demo.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //指明当前类为配置类,替代原来的bean.xml配置文件
public class MyAppConfig {

    @Bean  // 将方法返回值添加到容器中, 容器中这个方法的id就是方法名
    public HelloService helloService(){
        System.out.println("配置类@Bean给容器中添加组件, id: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        return new HelloService();
    }
}
