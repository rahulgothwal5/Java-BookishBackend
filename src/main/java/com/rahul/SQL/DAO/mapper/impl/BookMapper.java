package com.rahul.SQL.DAO.mapper.impl;

import com.rahul.SQL.DAO.domain.BookEntity;
import com.rahul.SQL.DAO.domain.dto.BookDTO;
import com.rahul.SQL.DAO.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookEntity, BookDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDTO mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDTO.class);
    }

    @Override
    public BookEntity mapFrom(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, BookEntity.class);
    }
}
