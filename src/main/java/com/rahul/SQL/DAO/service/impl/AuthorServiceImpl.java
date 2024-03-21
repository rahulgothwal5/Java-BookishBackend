package com.rahul.SQL.DAO.service.impl;

import com.rahul.SQL.DAO.domain.AuthorEntity;
import com.rahul.SQL.DAO.repo.AuthorRepository;
import com.rahul.SQL.DAO.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository){
        this.repository = repository;
    }


    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return repository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return null;
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean isExists(Long id) {
        return false;
    }

    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
