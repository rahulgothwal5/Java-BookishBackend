package com.rahul.SQL.DAO.controller;


import com.rahul.SQL.DAO.domain.AuthorEntity;
import com.rahul.SQL.DAO.domain.dto.AuthorDTO;
import com.rahul.SQL.DAO.domain.dto.BookDTO;
import com.rahul.SQL.DAO.mapper.impl.AuthorMapper;
import com.rahul.SQL.DAO.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    @Autowired
    private AuthorMapper mapper;

    @Autowired
    private AuthorService authorService;

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO body) {
        AuthorEntity authorEntity = mapper.mapFrom(body);
        AuthorEntity response = authorService.save(authorEntity);
        return new ResponseEntity<>(mapper.mapTo(response), HttpStatus.CREATED);
    }

    @GetMapping(path = "/authors")
    public ResponseEntity<List<AuthorDTO>> listAuthors() {
        return new ResponseEntity<>(authorService.findAll()
                .stream()
                .map(authorEntity -> mapper.mapTo(authorEntity))
                .toList(), HttpStatus.OK);
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id) {
        Optional<AuthorEntity> foundAuthor = authorService.findOne(id);
        return foundAuthor.map(authorEntity -> {
            return new ResponseEntity<>(mapper.mapTo(authorEntity), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> fullUpdateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorDTO authorDTO) {

        if (!authorService.isExists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        AuthorEntity authorEntity = mapper.mapFrom(authorDTO);
        authorEntity.setId(id);
        AuthorEntity res = authorService.save(authorEntity);
        return new ResponseEntity<>(mapper.mapTo(res),
                HttpStatus.OK);

    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> partialUpdate(
            @PathVariable Long id,
            @RequestBody AuthorDTO authorDTO) {
        if (!authorService.isExists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        AuthorEntity authorEntity = mapper.mapFrom(authorDTO);

        AuthorEntity res = authorService.partialUpdate(id, authorEntity);
        return new ResponseEntity<>(mapper.mapTo(res),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
