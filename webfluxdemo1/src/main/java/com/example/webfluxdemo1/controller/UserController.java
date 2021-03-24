package com.example.webfluxdemo1.controller;

import com.example.webfluxdemo1.entity.User;
import com.example.webfluxdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    // 注入Service
    @Autowired
    private UserService userService;

    // id查询
    @GetMapping("/user/{id}")
    public Mono<User> getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    // 所有查询
    @GetMapping("/users")
    public Flux<User> getAll(){
        return userService.getAllUser();
    }

    // 添加
    @PostMapping("/saveuser")
    public Mono<Void> saveUser(@RequestBody User user){
        Mono<User> userMono = Mono.just(user);
        return userService.saveUserInfo(userMono);
    }
}
