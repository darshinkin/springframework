package ru.example.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private final String name;
    private final String isbn;
}
