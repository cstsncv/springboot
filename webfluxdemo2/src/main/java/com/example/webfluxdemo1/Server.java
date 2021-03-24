package com.example.webfluxdemo1;

import com.example.webfluxdemo1.handler.UserHandler;
import com.example.webfluxdemo1.service.UserService;
import com.example.webfluxdemo1.service.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

public class Server {

    // 1. 创建Router
    public RouterFunction<ServerResponse> routingFunction(){

        //创建handler对象
        UserService userService = new UserServiceImpl();
        UserHandler handler = new UserHandler(userService);

        // 设置路由
        return RouterFunctions.route(
                GET("/user/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getUserByID)
                .andRoute(GET("/users").and(accept(MediaType.APPLICATION_JSON)), handler::getAllUser);
    }

    // 2. 创建服务器完成适配
    public void createReactorServer(){

        // 路由和handler适配
        RouterFunction<ServerResponse> route = routingFunction();
        HttpHandler httpHandler = toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        // 创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();
    }

    // 3. 最终调用
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("Enter to exit");
        System.in.read();
    }
}
