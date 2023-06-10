package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NonBlockingBookService implements BookService {
    private static final int HEAD_OFFICE_NON_BLOCKING_PORT = 3030;

    private static URI baseUri = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .path("/v1/books")
            .build()
            .encode()
            .toUri();

    public Mono<Book> getBook(int bookId) {
        URI getBooksUri = UriComponentsBuilder
                .fromUri(baseUri)
                .port(HEAD_OFFICE_NON_BLOCKING_PORT)
                .path("/{book-id}")
                .build()
                .expand(bookId)
                .encode()
                .toUri();

        System.out.println("getBooksUri = " + getBooksUri);
        System.out.println("##### " + "bookId : " + bookId + " " + LocalTime.now());

        return WebClient.create()
                .get()
                .uri(getBooksUri)
                .retrieve()
                .bodyToMono(Book.class);
    }

    @Override
    public List<Book> getBooks(int startBookId, int endBookId) {
        List<Book> books = new ArrayList<>();

        Flux.range(startBookId, endBookId)
                .flatMap(this::getBook)
                .doOnNext(books::add)
                .blockLast();

        return books;
    }
}
