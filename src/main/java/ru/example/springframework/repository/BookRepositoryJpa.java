package ru.example.springframework.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.example.springframework.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
@Transactional
@Service
@Data
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book findByName(String name) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.name = :name", Book.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Book findByIsbn(String isbn) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.isbn = :isbn", Book.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    @Override
    public void createBook(Book book) {
        em.persist(book);
    }

    @Override
    public int deleteBookByName(String name) {
        Query query = em.createQuery("delete from Book b where b.name = :name");
        query.setParameter("name", name);
        return query.executeUpdate();
    }
}
