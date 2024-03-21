package com.rahul.SQL.DAO.controller;


import com.rahul.SQL.DAO.domain.AuthorEntity;
import com.rahul.SQL.DAO.domain.dto.AuthorDTO;
import com.rahul.SQL.DAO.mapper.impl.AuthorMapperImpl;
import com.rahul.SQL.DAO.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    private AuthorMapperImpl mapper;

    @Autowired
    private AuthorService authorService;

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO body) {
        AuthorEntity authorEntity = mapper.mapFrom(body);
        AuthorEntity response = authorService.save(authorEntity);
        return new ResponseEntity<>(mapper.mapTo(response), HttpStatus.CREATED);
    }
}
