package ru.example.springframework.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.example.springframework.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;
    private final RowMapper<Author> authorRowMapper = (rs, rowNum) -> {
        int id = rs.getInt(1);
        String firstName = rs.getString(2);
        String lastName = rs.getString(3);
        return new Author(id, firstName, lastName);
    };

    @Override
    public Author getAuthorByName(String firstname) {
        Map<String, ?> map = Collections.singletonMap("firstname", firstname);
        return jdbc.queryForObject("select * from authors where firstname = :firstname", map, authorRowMapper);
    }

    @Override
    public List<Author> getAuthors() {
        List<Author> query = jdbc.query("select * from authors", authorRowMapper);
        return query;
    }

    @Override
    public int addAuthor(Author author) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("id", author.getId());
        map.put("firstname", author.getFirstName());
        map.put("lastname", author.getLastName());
        return jdbc.update("insert into authors(id, firstname, lastname) values (:id, :firstname, :lastname)", map);
    }
}
