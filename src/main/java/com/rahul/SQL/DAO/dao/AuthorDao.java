package com.rahul.SQL.DAO.dao;

import com.rahul.SQL.DAO.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    int create(Author author);

    int update(Author author, Long id);

    List<Author> find();

    Optional<Author> findOne(Long id);

    int delete(Long id);
}
