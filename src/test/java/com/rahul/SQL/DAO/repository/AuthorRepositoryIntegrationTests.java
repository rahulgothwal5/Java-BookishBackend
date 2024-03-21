package com.rahul.SQL.DAO.repository;

import com.rahul.SQL.DAO.TestDataUtil;
import com.rahul.SQL.DAO.domain.AuthorEntity;
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
public class AuthorRepositoryIntegrationTests {

    @Autowired
    private AuthorRepository underTest;


    @Test
    public void testCreateAuthorWithId() {
        final AuthorEntity authorEntity = createTestAuthorEntityA();
        final AuthorEntity savedAuthorEntity = underTest.save(authorEntity);
        assertThat(authorEntity).isEqualTo(savedAuthorEntity);
    }

    @Test
    public void testCreateAuthorWithoutId() {
        final AuthorEntity authorEntity = createTestAuthorEntityA();
        authorEntity.setId(null);
        final AuthorEntity savedAuthorEntity = underTest.save(authorEntity);
        assertThat(authorEntity).isEqualTo(savedAuthorEntity);
    }

    @Test
    public void testCreateAndFindAuthorById() {
        final AuthorEntity authorEntity = createTestAuthorB();
        final AuthorEntity savedAuthorEntity = underTest.save(authorEntity);
        final Optional<AuthorEntity> result = underTest.findById(savedAuthorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedAuthorEntity);
    }

    @Test
    public void testCreateAndFindAllAuthors() {
        final AuthorEntity testAuthorAEntity = underTest.save(createTestAuthorEntityA());
        final AuthorEntity testAuthorBEntity = underTest.save(createTestAuthorB());
        final AuthorEntity testAuthorCEntity = underTest.save(createTestAuthorC());
        final List<AuthorEntity> expected = List.of(testAuthorAEntity, testAuthorBEntity, testAuthorCEntity);

        final List<AuthorEntity> result = underTest.findAll();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testCreateUpdateAuthor() {
        final AuthorEntity testAuthorAEntity = underTest.save(createTestAuthorEntityA());
        testAuthorAEntity.setName("Updated");
        underTest.save(testAuthorAEntity);
        final Optional<AuthorEntity> result = underTest.findById(testAuthorAEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testAuthorAEntity);
    }

    @Test
    public void testCreateDeleteAuthor() {
        final AuthorEntity testAuthorAEntity = underTest.save(createTestAuthorEntityA());
        final Optional<AuthorEntity> saveResult = underTest.findById(testAuthorAEntity.getId());
        assertThat(saveResult).isPresent();

        underTest.deleteById(testAuthorAEntity.getId());
        final Optional<AuthorEntity> result = underTest.findById(testAuthorAEntity.getId());
        assertThat(result).isNotPresent();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        AuthorEntity testAuthorAEntity = TestDataUtil.createTestAuthorEntityA();
        underTest.save(testAuthorAEntity);
        AuthorEntity testAuthorBEntity = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorBEntity);
        AuthorEntity testAuthorCEntity = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorCEntity);

        Iterable<AuthorEntity> result = underTest.ageLessThan(50);
        assertThat(result).containsExactly(testAuthorBEntity, testAuthorCEntity);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        AuthorEntity testAuthorAEntity = TestDataUtil.createTestAuthorEntityA();
        underTest.save(testAuthorAEntity);
        AuthorEntity testAuthorBEntity = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorBEntity);
        AuthorEntity testAuthorCEntity = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorCEntity);

        Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreaterThan(50);
        assertThat(result).containsExactly(testAuthorAEntity);
    }
}
