package com.rahul.SQL.DAO.dao.impl;

import com.rahul.SQL.DAO.TestDataUtil;
import com.rahul.SQL.DAO.domain.Author;
import com.rahul.SQL.DAO.repo.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.rahul.SQL.DAO.TestDataUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTests {

    @Autowired
    private AuthorRepository underTest;


    @Test
    public void testCreateAuthorWithId() {
        final Author author = createTestAuthorA();
        final Author savedAuthor = underTest.save(author);
        assertThat(author).isEqualTo(savedAuthor);
    }

    @Test
    public void testCreateAuthorWithoutId() {
        final Author author = createTestAuthorA();
        author.setId(null);
        final Author savedAuthor = underTest.save(author);
        assertThat(author).isEqualTo(savedAuthor);
    }

    @Test
    public void testCreateAndFindAuthorById() {
        final Author author = createTestAuthorB();
        final Author savedAuthor = underTest.save(author);
        final Optional<Author> result = underTest.findById(savedAuthor.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedAuthor);
    }

    @Test
    public void testCreateAndFindAllAuthors() {
        final Author testAuthorA = underTest.save(createTestAuthorA());
        final Author testAuthorB = underTest.save(createTestAuthorB());
        final Author testAuthorC = underTest.save(createTestAuthorC());
        final List<Author> expected = List.of(testAuthorA, testAuthorB, testAuthorC);

        final List<Author> result = underTest.findAll();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testCreateUpdateAuthor() {
        final Author testAuthorA = underTest.save(createTestAuthorA());
        testAuthorA.setName("Updated");
        underTest.save(testAuthorA);
        final Optional<Author> result = underTest.findById(testAuthorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testAuthorA);
    }

    @Test
    public void testCreateDeleteAuthor() {
        final Author testAuthorA = underTest.save(createTestAuthorA());
        final Optional<Author> saveResult = underTest.findById(testAuthorA.getId());
        assertThat(saveResult).isPresent();

        underTest.deleteById(testAuthorA.getId());
        final Optional<Author> result = underTest.findById(testAuthorA.getId());
        assertThat(result).isNotPresent();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        Author testAuthorA = TestDataUtil.createTestAuthorA();
        underTest.save(testAuthorA);
        Author testAuthorB = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorB);
        Author testAuthorC = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorC);

        Iterable<Author> result = underTest.ageLessThan(50);
        assertThat(result).containsExactly(testAuthorB, testAuthorC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        Author testAuthorA = TestDataUtil.createTestAuthorA();
        underTest.save(testAuthorA);
        Author testAuthorB = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorB);
        Author testAuthorC = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorC);

        Iterable<Author> result = underTest.findAuthorsWithAgeGreaterThan(50);
        assertThat(result).containsExactly(testAuthorA);
    }
}
