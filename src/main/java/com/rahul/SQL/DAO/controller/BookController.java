package com.rahul.SQL.DAO.controller;

import com.rahul.SQL.DAO.domain.AuthorEntity;
import com.rahul.SQL.DAO.domain.BookEntity;
import com.rahul.SQL.DAO.domain.dto.BookDTO;
import com.rahul.SQL.DAO.mapper.impl.BookMapper;
import com.rahul.SQL.DAO.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class BookController {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookService bookService;

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> createUpdateBook(
            @PathVariable String isbn,
            @RequestBody BookDTO bookDTO
    ) {
        BookEntity book = bookMapper.mapFrom(bookDTO);
        boolean bookExists = bookService.isExists(isbn);
        BookEntity res = bookService.createUpdateBook(isbn, book);
        BookDTO savedUpdatedBookDto = bookMapper.mapTo(res);

        if (bookExists) {
            return new ResponseEntity<>(savedUpdatedBookDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedUpdatedBookDto, HttpStatus.CREATED);

        }
    }


    @GetMapping(path = "/books")
    public ResponseEntity<List<BookDTO>> listAuthors() {
        return new ResponseEntity<>(bookService.findAll()
                .stream()
                .map(bookEntity -> bookMapper.mapTo(bookEntity))
                .toList(), HttpStatus.OK);
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> getAuthor(@PathVariable String isbn) {
        Optional<BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map(bookEntity -> {
            return new ResponseEntity<>(bookMapper.mapTo(bookEntity), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> partialUpdateBook(
            @PathVariable String isbn,
            @RequestBody BookDTO bookDTO
    ) {
        if (!bookService.isExists(isbn))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        BookEntity bookEntity = bookMapper.mapFrom(bookDTO);

        BookEntity res = bookService.partialUpdate(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(res),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn) {
        bookService.delete(isbn);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
