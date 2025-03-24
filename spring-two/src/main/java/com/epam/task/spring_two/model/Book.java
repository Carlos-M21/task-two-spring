package com.epam.task.spring_two.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Column(name = "year_of_publish")
    private int yearOfPublish;
    private double price;
    private String classification;
    @Column(name = "is_available")
    private boolean isAvailable;
}
