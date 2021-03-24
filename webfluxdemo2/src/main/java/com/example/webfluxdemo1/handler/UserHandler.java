package com.example.webfluxdemo1.handler;

import com.example.webfluxdemo1.entity.User;
import com.example.webfluxdemo1.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    // 根据id查询
    public Mono<ServerResponse> getUserByID(ServerRequest request){

        // 获取id
        int id = Integer.parseInt(request.pathVariable("id"));

        //空值处理
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        // 调用service方法获取数据
        Mono<User> userMono = this.userService.getUserById(id);

        // 将userMono转换成流返回
        // 使用Reactor操作符flatMap
        return userMono.flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fromObject(person))).switchIfEmpty(notFound);
//        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userMono, User.class).switchIfEmpty(notFound);

    }

    // 查询所有
    public Mono<ServerResponse> getAllUser(ServerRequest request){
        // 调用service得结果
        Flux<User> allUser = this.userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(allUser, User.class);
    }

    // 添加
    public Mono<ServerResponse> saveUser(ServerRequest request){
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(this.userService.saveUserInfo(userMono));
    }
}
