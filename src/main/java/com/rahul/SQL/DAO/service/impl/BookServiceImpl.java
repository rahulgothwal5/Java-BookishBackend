package com.rahul.SQL.DAO.service.impl;

import com.rahul.SQL.DAO.domain.BookEntity;
import com.rahul.SQL.DAO.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    @Override
    public BookEntity createUpdateBook(String isbn, BookEntity book) {
        return null;
    }

    @Override
    public List<BookEntity> findAll() {
        return null;
    }

    @Override
    public Page<BookEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return Optional.empty();
    }

    @Override
    public boolean isExists(String isbn) {
        return false;
    }

    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity) {
        return null;
    }

    @Override
    public void delete(String isbn) {

    }
}
