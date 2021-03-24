package com.example.demoreactor8.reactor8;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestReactor {
    public static void main(String[] args) {

        //
        Flux.just(1,2,3,4).subscribe(System.out::println);
        Mono.just(1).subscribe(System.out::println);

        // 数组
        Integer[] array = {1,2,3,4};
        Flux.fromArray(array);

        //List
        List<Integer> list = Arrays.asList(array);
        Flux.fromIterable(list);

        //Stream
        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);
    }
}
