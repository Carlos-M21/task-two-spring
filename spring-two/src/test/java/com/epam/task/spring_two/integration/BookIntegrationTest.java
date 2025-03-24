package com.epam.task.spring_two.integration;

import com.epam.task.spring_two.model.Book;
import com.epam.task.spring_two.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class BookIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testBookFlow() {
        Book newBook = new Book(10L, "Harry Potter", "J.K. Rowling", 2001, 19.99, "Fantasy", true);
        bookRepository.save(newBook);
        Book foundBook = bookRepository.findById(10L).orElse(null);
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("Harry Potter");
        assertThat(foundBook.getYearOfPublish()).isEqualTo(2001);
        bookRepository.deleteById(10L);
        Book foundBookAfterDelete = bookRepository.findById(10L).orElse(null);
        assertThat(foundBookAfterDelete).isNull();
    }
}