package com.example.demo.controller;


import com.example.demo.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/books")
public class HeadOfficeNonBlockController {
    private final URI baseUri = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port(4040)
            .path("/v1/books")
            .build()
            .encode()
            .toUri();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public Mono<Book> getBook(@PathVariable("book-id") int bookId){
        URI getBookUri = UriComponentsBuilder.fromUri(baseUri)
                .path("/{book-id}")
                .build()
                .expand(bookId)
                .encode()
                .toUri();    // http://localhost:4040/v1/books/{book-id}

        return WebClient.create()
                .get()
                .uri(getBookUri)
                .retrieve()
                .bodyToMono(Book.class);
    }
}
