package ru.example.springframework.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.springframework.domain.Book;

import javax.persistence.NoResultException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = "ru.example.springframework")
public class BookRepositoryJpaIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepositoryJpa;

    @Test
    public void whenFindByName_thenReturnBook() {
        // given
        Book book = new Book(111, "Fathers and Sons", "978-5-699-74085-7");
        entityManager.persist(book);
        entityManager.flush();

        // when
        Book found = bookRepositoryJpa.findByName(book.getName());

        // then
        assertThat(found.getName()).isEqualTo(book.getName());
        assertThat(found.getIsbn()).isEqualTo(book.getIsbn());
    }

    @Test
    public void whenCreate_thenReturnCreatedBook() {
        // given
        Book book = new Book(111, "Fathers and Sons", "978-5-699-74085-7");
        bookRepositoryJpa.createBook(book);

        // when
        Book found = bookRepositoryJpa.findByName(book.getName());

        // then
        assertThat(found.getName()).isEqualTo(book.getName());
        assertThat(found.getIsbn()).isEqualTo(book.getIsbn());
    }

    @Test(expected = NoResultException.class)
    public void whenDelete_thenDontReturnBook() {
        // given
        Book book = new Book(111, "Fathers and Sons", "978-5-699-74085-7");
        bookRepositoryJpa.createBook(book);

        // when
        Book found = bookRepositoryJpa.findByName(book.getName());

        // then
        assertThat(found.getName()).isEqualTo(book.getName());
        assertThat(found.getIsbn()).isEqualTo(book.getIsbn());

        // when
        bookRepositoryJpa.deleteBookByName(book.getName());

        // then
        bookRepositoryJpa.findByIsbn(book.getIsbn());
    }
}
