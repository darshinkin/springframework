package ru.example.springframework.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.example.springframework.domain.Book;
import ru.example.springframework.repository.BookRepository;

@ShellComponent
public class BookCommands {
    private final BookRepository repository;

    @Autowired
    public BookCommands(BookRepository repository) {
        this.repository = repository;
    }

    @ShellMethod("Create book")
    public String createBook(@ShellOption String name,
                             @ShellOption String isbn) {
        Book book = new Book();
        book.setName(name);
        book.setIsbn(isbn);
        repository.createBook(book);
        return repository.findByIsbn(book.getIsbn()).toString();
    }

    @ShellMethod("Delete book by name")
    public String deleteBookByName(@ShellOption String name) {
        Book book = repository.findByName(name);
        repository.deleteBookByName(name);
        return "Deleted book: " + book;
    }
}
