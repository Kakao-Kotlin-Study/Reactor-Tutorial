package com.example.demo.controller;

import com.example.demo.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/books")
public class BranchOfficeController {
    private Map<Integer, Book> bookMap  = new HashMap<>(){
        {
            put(1, new Book(1, "Book1", 10000));
            put(2, new Book(2, "Book2", 20000));
            put(3, new Book(3, "Book3", 30000));
            put(4, new Book(4, "Book4", 40000));
            put(5, new Book(5, "Book5", 50000));
        }
    };

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public ResponseEntity<Book> getBook(@PathVariable("book-id") Integer bookId)
            throws InterruptedException{

        Thread.sleep(3000);
        return ResponseEntity.ok(bookMap.get(bookId));

    }
}
