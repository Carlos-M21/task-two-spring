package com.epam.task.spring_two.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String title;
    private String author;
    private int yearOfPublish;
    private double price;
    private String classification;
    private boolean isAvailable;
}
