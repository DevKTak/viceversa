package com.viceversa.book.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.viceversa.book.dto.FindBookResponse;
import com.viceversa.book.service.BookService;

@WebMvcTest(BookController.class)
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Test
	@DisplayName("Book 조회 RestApi 호출")
	public void getBooks() throws Exception {
		// given
		List<List<FindBookResponse>> mockBooks = new ArrayList<>(); // 원하는 Mock 데이터 생성
		doReturn(mockBooks).when(bookService).getBooks(); // Mock 데이터를 반환하도록 설정

		mockMvc.perform(get("/api/books"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
}

