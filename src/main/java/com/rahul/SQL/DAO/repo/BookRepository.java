package com.rahul.SQL.DAO.repo;

import com.rahul.SQL.DAO.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String > {
}
