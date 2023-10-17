package com.viceversa.book.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FindBookResponse(String title, int price, String isbn) {
}
