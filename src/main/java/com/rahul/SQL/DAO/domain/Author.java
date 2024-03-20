package com.rahul.SQL.DAO.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="author_id_seq")
    private Long id;

    private String name;

    private Integer age;

}