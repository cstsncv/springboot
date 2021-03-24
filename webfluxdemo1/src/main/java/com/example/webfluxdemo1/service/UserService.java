package com.example.webfluxdemo1.service;

import com.example.webfluxdemo1.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//创建用户操作接口定义操作方法
public interface UserService {

    //根据id查询用户
    Mono<User> getUserById(int id);

    // 查询所有用户
    Flux<User> getAllUser();

    // 添加用户
    Mono<Void> saveUserInfo(Mono<User> user);
}
