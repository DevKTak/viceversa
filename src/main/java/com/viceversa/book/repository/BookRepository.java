package com.viceversa.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viceversa.book.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
