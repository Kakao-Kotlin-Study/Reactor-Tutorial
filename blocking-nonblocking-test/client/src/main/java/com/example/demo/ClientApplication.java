package com.example.demo;

import com.example.demo.service.BlockingBookService;
import com.example.demo.service.BookService;

public class ClientApplication {
	private static final BookService bookService = new BlockingBookService();

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		bookService.getBooks(1, 5)
				.forEach(System.out::println);

		long endTime = System.currentTimeMillis();
		System.out.println("실행 시간: " + ((endTime - startTime) / 1000.0) + "초");
	}
}
