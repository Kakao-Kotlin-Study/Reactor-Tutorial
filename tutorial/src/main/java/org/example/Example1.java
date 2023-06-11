package org.example;

import reactor.core.publisher.Mono;

public class Example1 {
    public static void main(String[] args) {
        Mono.empty()
            .subscribe(
                none -> System.out.println("# emitted onNext signal"),
                error -> System.out.println("# emitted onError signal"),
                () -> System.out.println("# emitted onComplete signal")
            );
    }
}
