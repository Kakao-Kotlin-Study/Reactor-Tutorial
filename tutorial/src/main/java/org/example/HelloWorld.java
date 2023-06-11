package org.example;

import reactor.core.publisher.Flux;

public class HelloWorld {
    public static void main(String[] args) {
        Flux.just("Hello", " ", "World!")    // Flux 반환
            .map(String::toLowerCase)   // Flux 반환
            .subscribe(System.out::print);
    }
}
