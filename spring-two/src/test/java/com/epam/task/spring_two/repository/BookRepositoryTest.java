package com.epam.task.spring_two.repository;

import com.epam.task.spring_two.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindByTitle() {
        Book foundBook = bookRepository.findByTitle("Prodigy");
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getAuthor()).isEqualTo("Marie Lu");
    }

    @Test
    public void testBookUpdate() {
        Book foundBook = bookRepository.findById(6L).orElse(null);
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("The Boys Of Riverside");
        assertThat(foundBook.getYearOfPublish()).isEqualTo(2024);
        Book updatedBook = new Book(6L, "Shadow Hunters City Of Ashes", "Cassandra Clare", 2023, 16.99, "fiction", true);
        bookRepository.save(updatedBook);
        Book foundUpdatedBook = bookRepository.findById(6L).orElse(null);
        assertThat(foundUpdatedBook).isNotNull();
        assertThat(foundUpdatedBook.getTitle()).isEqualTo(updatedBook.getTitle());
        assertThat(foundUpdatedBook.getYearOfPublish()).isEqualTo(updatedBook.getYearOfPublish());
    }

    @Test
    public void testBookDelete() {
        assertThat(bookRepository.existsById(5L)).isTrue();
        bookRepository.deleteById(5L);
        assertThat(bookRepository.existsById(5L)).isFalse();
    }
}