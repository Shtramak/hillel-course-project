package com.courses.tellus.persistence.repositories.hibernate;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import com.courses.tellus.config.hibernate.HibernateUtils;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.persistence.repository.hibernate.SubjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubjectRepoIntegrationTest {

    private SubjectRepository repository;
    private Subject subject;

    @BeforeEach
    void setup() {
        EntityManager manager = HibernateUtils.getManagerFactory().createEntityManager();
        repository = new SubjectRepository(manager);
        subject = new Subject("math", "fdsfs", true,
                LocalDate.of(2000, 05, 12));
        if (repository.getAll().size() == 0) {
            repository.insert(subject);
        }
    }

    @AfterEach
    void clean(){
        while (repository.getAll().size() > 1) {
            final List<Subject> subjects = repository.getAll();
            repository.delete(subjects.get(subjects.size() - 1).getSubjectId());
        }
    }

    @Test
    void findAllTestAndReturnEntityList() {
        assertEquals(1, repository.getAll().size());
    }

    @Test
    void getByIdTestAndReturnEntity() {
        assertTrue(repository.getById(1L).isPresent());
    }

    @Test
    void mergeTestAndReturnSuccessfulValue() {
        subject.setValid(false);
        assertEquals(1, repository.update(subject));
    }

    @Test
    void persistTestAndReturnSuccessfulValue() {
        assertEquals(1, repository.insert(subject));
    }

    @Test
    void removeTestAndReturnSuccessfulValue() {
        assertEquals(1, repository.delete(1L));
    }


    @Test
    void findByIdTestAndThrowException() {
        assertFalse(repository.getById(null).isPresent());
    }

    @Test
    void mergeTestAndThrowException() {
        assertEquals(0, repository.update(null));
    }

    @Test
    void persistTestAndThrowException() {
        assertEquals(0, repository.insert(null));
    }

    @Test
    void removeTestAndThrowException() {
        assertEquals(0, repository.delete(null));
    }
}
