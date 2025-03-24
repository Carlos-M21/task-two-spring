package com.epam.task.spring_two.service;

import com.epam.task.spring_two.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    Book findByTitle(String tittle);
}