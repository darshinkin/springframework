package ru.example.springframework.dao;

import ru.example.springframework.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author getAuthorByName(String name);
    List<Author> getAuthors();
    int addAuthor(Author author);
}
