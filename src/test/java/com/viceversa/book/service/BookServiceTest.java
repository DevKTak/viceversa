package com.viceversa.book.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viceversa.book.domain.Book;
import com.viceversa.book.dto.FindBookResponse;
import com.viceversa.book.repository.BookRepository;

@SpringBootTest
class BookServiceTest {

	@InjectMocks // 가짜 객체 주입을 위한 (@Mock 또는 @Spy로 생성된 가짜 객체를 자동으로 주입시켜주는 어노테이션)
	private BookService bookService;

	@Mock // 가짜 객체 생성을 위해
	private BookRepository bookRepository;

	@Spy
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Book 테이블 조회 성공")
	void getBooks_success() {
		// given
		Book book = Book.builder()
			.id(1L)
			.bookJsonData(
				"[{\"title\":\"testTitle\", \"price\":1000, \"isbn\":\"a1234\"}, {\"title\":\"testTitle2\", \"price\":2000, \"isbn\":\"b1234\"}]")
			.build();
		Book book2 = Book.builder()
			.id(2L)
			.bookJsonData("[{\"title\":\"testTitle3\", \"price\":3000, \"isbn\":\"c1234\"}]")
			.build();
		doReturn(List.of(book, book2)).when(bookRepository).findAll(); // 가짜 객체가 특정한 값을 반환해야 하는 경우

		// when
		List<List<FindBookResponse>> books = bookService.getBooks();

		// then
		assertThat(books).isNotNull();
		assertThat(books).isNotEmpty();
		assertThat(books).hasSize(2);
		assertThat(books.get(0).get(0).title()).isEqualTo("testTitle");
	}

	@Test
	@DisplayName("Book 테이블 조회 실패")
	void getBooks_empty() {
		// given

		// mocking
		doReturn(new ArrayList<>()).when(bookRepository).findAll();

		// when

		// then
		assertThatThrownBy(() -> bookService.getBooks()).isInstanceOf(NoSuchElementException.class)
			.hasMessage("데이터가 없습니다.");
	}
}
