package com.example.webfluxdemo1.service;

import com.example.webfluxdemo1.entity.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserServiceImpl implements UserService{

    // 创建Map集合存储数据
    private final Map<Integer, User> users = new HashMap<>();

    public UserServiceImpl() {
        this.users.put(1, new User("Tom", "man", 22));
        this.users.put(2, new User("Jerry", "man", 33));
        this.users.put(3, new User("Tony", "man", 23));
    }

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(this.users.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        return userMono.doOnNext(person -> {
            //向集合内放值
            int i = this.users.size() + 1;
            this.users.put(i, person);
        }).thenEmpty(Mono.empty());  // 清空Mono, 终止信号
    }
}
