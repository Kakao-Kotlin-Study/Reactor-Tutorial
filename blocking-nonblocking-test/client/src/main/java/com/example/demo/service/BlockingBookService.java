package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlockingBookService implements BookService{
    private static final int HEAD_OFFICE_BLOCKING_PORT = 2020;

    private static URI baseUri = UriComponentsBuilder.newInstance()
            .scheme("http")
            .port(HEAD_OFFICE_BLOCKING_PORT)
            .host("localhost")
            .path("/v1/books")
            .build()
            .encode()
            .toUri();

    public Book getBook(int bookId) {
        RestTemplate restTemplate = new RestTemplate();

        URI getBooksUri = UriComponentsBuilder
                .fromUri(baseUri)
                .port(HEAD_OFFICE_BLOCKING_PORT)
                .path("/{book-id}")
                .build()
                .expand(bookId)
                .encode()
                .toUri();

        System.out.println("getBooksUri = " + getBooksUri);
        System.out.println("##### " + "bookId : " + bookId + " " + LocalTime.now());

        return restTemplate
                .getForEntity(getBooksUri, Book.class)
                .getBody();
    }

    @Override
    public List<Book> getBooks(int startBookId, int endBookId) {
        return IntStream.range(startBookId, endBookId + 1)
                .mapToObj(this::getBook)
                .collect(Collectors.toList());
    }
}
