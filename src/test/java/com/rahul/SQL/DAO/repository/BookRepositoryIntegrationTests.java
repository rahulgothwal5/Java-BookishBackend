package com.rahul.SQL.DAO.repository;

import com.rahul.SQL.DAO.domain.AuthorEntity;
import com.rahul.SQL.DAO.domain.BookEntity;
import com.rahul.SQL.DAO.repo.AuthorRepository;
import com.rahul.SQL.DAO.repo.BookRepository;
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
public class BookRepositoryIntegrationTests {

    @Autowired
    private AuthorRepository authorDao;
    
    @Autowired
    private BookRepository underTest;

    @Test
    public void testCreateBook() {
        final AuthorEntity authorEntity = createTestAuthorEntityA();
        final BookEntity bookEntity = createTestBookEntityA(authorEntity);
        final BookEntity result = underTest.save(bookEntity);
        assertThat(result).isEqualTo(bookEntity);
    }

    @Test
    public void testCreateAndFindBook() {
        final AuthorEntity authorEntity = createTestAuthorEntityA();
        final BookEntity bookEntity = createTestBookEntityA(authorEntity);
        final BookEntity savedBookEntity = underTest.save(bookEntity);
        final Optional<BookEntity> result = underTest.findById(savedBookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedBookEntity);
    }

    @Test
    public void testCreateAndFindAllBook() {
        final AuthorEntity authorEntity = createTestAuthorEntityA();
        final BookEntity savedBookAEntity = underTest.save(createTestBookEntityA(authorEntity));
        final BookEntity savedBookBEntity = underTest.save(createTestBookB(authorEntity));
        final BookEntity savedBookCEntity = underTest.save(createTestBookC(authorEntity));
        final List<BookEntity> expected = List.of(savedBookAEntity, savedBookBEntity, savedBookCEntity);

        final List<BookEntity> result = underTest.findAll();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testCreateUpdateBook() {
        final AuthorEntity authorEntity = createTestAuthorEntityA();
        final BookEntity savedBookEntity = underTest.save(createTestBookEntityA(authorEntity));
        savedBookEntity.setTitle("A new title");
        underTest.save(savedBookEntity);

        final Optional<BookEntity> result = underTest.findById(savedBookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedBookEntity);
    }

    @Test
    public void testCreateDeleteBook() {
        final AuthorEntity authorEntity = createTestAuthorEntityA();
        final BookEntity savedBookEntity = underTest.save(createTestBookEntityA(authorEntity));
        final Optional<BookEntity> saveResult = underTest.findById(savedBookEntity.getIsbn());
        assertThat(saveResult).isPresent();

        underTest.deleteById(savedBookEntity.getIsbn());

        final Optional<BookEntity> afterDeleteResult = underTest.findById(savedBookEntity.getIsbn());
        assertThat(afterDeleteResult).isNotPresent();
    }
}
