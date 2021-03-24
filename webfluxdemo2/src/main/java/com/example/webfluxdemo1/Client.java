package com.example.webfluxdemo1;

import com.example.webfluxdemo1.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class Client {
    public static void main(String[] args) {

        WebClient webClient = WebClient.create("http://127.0.0.1:64090");

        // 根据id
        String id = "1";
        User userResult = webClient.get().uri("/user/{id}", id).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(User.class).block();
        System.out.println(userResult);

        // 查询所有
        Flux<User> users = webClient.get().uri("/users").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(User.class);
        users.map(User::toString).buffer().doOnNext(System.out::println).blockFirst();
    }
}
