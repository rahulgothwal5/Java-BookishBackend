package com.rahul.SQL.DAO.service.impl;

import com.rahul.SQL.DAO.domain.AuthorEntity;
import com.rahul.SQL.DAO.repo.AuthorRepository;
import com.rahul.SQL.DAO.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;


    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return repository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);
        return repository.findById(id).map(existingAuthor -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
            return repository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author does not exists"));

    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
