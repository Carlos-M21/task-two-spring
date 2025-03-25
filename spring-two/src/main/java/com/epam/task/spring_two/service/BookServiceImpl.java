package com.epam.task.spring_two.service;

import com.epam.task.spring_two.dto.BookDTO;
import com.epam.task.spring_two.model.Book;
import com.epam.task.spring_two.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Override
    public Book createBook(BookDTO book) {
        Book bookDomain = Book.toDomain(book);
        return bookRepository.save(bookDomain);
    }

    @Override
    public Book updateBook(Long id, BookDTO book) {
        Book bookDomain = Book.toDomain(book);
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookDomain.setId(id);
        return bookRepository.save(bookDomain);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public Book findByTitle(String tittle) {
        return bookRepository.findByTitle(tittle);
    }
}