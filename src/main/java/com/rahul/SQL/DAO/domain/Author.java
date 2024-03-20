package com.rahul.SQL.DAO.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private Long id;

    private String name;

    private Integer age;

}