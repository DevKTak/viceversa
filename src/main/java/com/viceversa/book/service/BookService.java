package com.viceversa.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viceversa.book.domain.Book;
import com.viceversa.book.dto.FindBookResponse;
import com.viceversa.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	private final ObjectMapper objectMapper;

	public List<List<FindBookResponse>> getBooks() {
		List<Book> books = bookRepository.findAll();

		if (books.isEmpty()) {
			throw new NoSuchElementException("데이터가 없습니다.");
		}
		List<List<FindBookResponse>> list = new ArrayList<>();

		for (Book book : books) {
			try {
				list.add(objectMapper.readValue(book.getBookJsonData(), new TypeReference<>() {
				}));
			} catch (JsonProcessingException e) {
				throw new IllegalArgumentException();
			}
		}
		return list;
	}
}
