package org.example;

import reactor.core.publisher.Flux;

import java.util.Arrays;

public class ColdSequence {
    public static void main(String[] args) {
        Flux<String> coldFlux = Flux
                .fromIterable(Arrays.asList("Korea", "Japen", "Chinese"))
                .map(String::toLowerCase);

        coldFlux.subscribe(country -> System.out.println("# Subscriber1 = " + country));
        System.out.println("--------------------------------");
        coldFlux.subscribe(country -> System.out.println("# Subscriber2 = " + country));
    }
}
