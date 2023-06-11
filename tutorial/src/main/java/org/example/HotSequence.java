package org.example;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotSequence {
    public static void main(String[] args) throws InterruptedException{
        String[] singers = {"Singer A", "Singer B", "Singer C", "Singer D"};
        System.out.println("# Begin concert:");

        Flux<String> concertFlux = Flux.fromArray(singers)
                .delayElements(Duration.ofSeconds(1))
                .share();

        concertFlux.subscribe(
            singer -> System.out.println("# Subscriber1 is watching " + singer + "'s song")
        );

        Thread.sleep(2500);
        concertFlux.subscribe(
            singer -> System.out.println("# Subscriber2 is watching " + singer + "'s song")
        );

        Thread.sleep(3000);
    }
}
