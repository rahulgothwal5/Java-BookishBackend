package com.rahul.SQL.DAO.dao;

import com.rahul.SQL.DAO.domain.Author;
import com.rahul.SQL.DAO.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    int create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> find();
    int update(String isbn, Book book);

    int delete(String isbn);
}
