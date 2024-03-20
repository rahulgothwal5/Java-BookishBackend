package com.rahul.SQL.DAO.dao.impl;

import com.rahul.SQL.DAO.dao.AuthorDao;
import com.rahul.SQL.DAO.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int create(Author author) {
        return jdbcTemplate.update(
                "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                author.getId(), author.getName(), author.getAge()
        );
    }

    @Override
    public int update(Author author, Long id) {
        return jdbcTemplate.update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                author.getId(), author.getName(), author.getAge(), id
        );
    }

    @Override
    public List<Author> find() {
        return jdbcTemplate.query(
                "SELECT id, name, age FROM authors",
                new AuthorRowMapper()
        );
    }

    @Override
    public Optional<Author> findOne(Long id) {
        List<Author> results = jdbcTemplate.query(
                "SELECT id, name, age FROM authors WHERE id = ? LIMIT 1",
                new AuthorRowMapper(), id);

        return results.stream().findFirst();
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(
                "DELETE FROM authors where id = ?",
                id
        );
    }

    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
