package com.example.demo.controller;

import com.example.demo.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/books")
public class HeadOfficeBlockController {
    private final RestTemplateBuilder restTemplateBuilder;
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
    public ResponseEntity<Book> getBook(@PathVariable("book-id") int bookId){
        URI getBookUri = UriComponentsBuilder.fromUri(baseUri)
            .path("/{book-id}")
            .build()
            .expand(bookId)
            .encode()
            .toUri();    // http://localhost:7070/v1/books/{book-id}

        return ResponseEntity.ok(
            restTemplateBuilder
                .build()
                .getForEntity(getBookUri, Book.class)
                .getBody()
        );
    }
}
