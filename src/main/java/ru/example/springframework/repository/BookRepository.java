package ru.example.springframework.repository;

import ru.example.springframework.domain.Book;

public interface BookRepository {
    Book findByName(String name);
    Book findByIsbn(String isbn);
    void createBook(Book book);
    int deleteBookByName(String name);
}
