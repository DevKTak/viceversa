package com.viceversa.book.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viceversa.book.dto.FindBookResponse;
import com.viceversa.book.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	@GetMapping
	public List<List<FindBookResponse>> getBooks() {
		return bookService.getBooks();
	}
}
