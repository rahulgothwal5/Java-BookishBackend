package com.rahul.SQL.DAO.mapper.impl;

import com.rahul.SQL.DAO.domain.AuthorEntity;
import com.rahul.SQL.DAO.domain.dto.AuthorDTO;
import com.rahul.SQL.DAO.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuthorDTO mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity , AuthorDTO.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO , AuthorEntity.class);
    }
}
