package com.example.demo.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String name;
    private Integer price;
}
