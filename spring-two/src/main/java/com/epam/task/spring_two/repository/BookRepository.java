package com.epam.task.spring_two.repository;

import com.epam.task.spring_two.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String tittle);
}