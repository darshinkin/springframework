package ru.example.springframework.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.springframework.domain.Author;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testGetAuthorByName() {
        Author author = authorDao.getAuthorByName("Lev");

        assertNotNull(author);
        assertEquals("Lev", author.getFirstName());
        assertEquals("Tolstoy", author.getLastName());
        assertEquals(1, author.getId());
    }

    @Test
    public void testGetAuthors() {
        List<Author> authors = authorDao.getAuthors();

        assertThat(1, is(authors.size()));
    }

    @Test
    public void testAddAuthor() {
        authorDao.addAuthor(new Author(2, "Alexandr", "Pushkin"));

        assertThat(2, is(authorDao.getAuthors().size()));
        assertThat(2, is(authorDao.getAuthorByName("Alexandr").getId()));
        assertThat("Pushkin", is(authorDao.getAuthorByName("Alexandr").getLastName()));
    }
}
