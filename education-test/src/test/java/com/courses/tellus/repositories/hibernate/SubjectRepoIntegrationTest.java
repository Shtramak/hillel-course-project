package com.courses.tellus.repositories.hibernate;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import com.courses.tellus.config.hibernate.HibernateUtils;
import com.courses.tellus.model.Subject;
import com.courses.tellus.repository.hibernate.SubjectRepository;
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
        if (repository.findAll().size() == 0) {
            repository.persist(subject);
        }
    }

    @AfterEach
    void clean(){
        while (repository.findAll().size() > 1) {
            final List<Subject> subjects = repository.findAll();
            repository.remove(subjects.get(subjects.size() - 1).getSubjectId());
        }
    }

    @Test
    void findAllTestAndReturnEntityList() {
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void getByIdTestAndReturnEntity() {
        assertTrue(repository.findById(1L).isPresent());
    }

    @Test
    void mergeTestAndReturnSuccessfulValue() {
        subject.setValid(false);
        assertEquals(1, repository.merge(subject));
    }

    @Test
    void persistTestAndReturnSuccessfulValue() {
        assertEquals(1, repository.persist(subject));
    }

    @Test
    void removeTestAndReturnSuccessfulValue() {
        assertEquals(1, repository.remove(1L));
    }


    @Test
    void findByIdTestAndThrowException() {
        assertFalse(repository.findById(null).isPresent());
    }

    @Test
    void mergeTestAndThrowException() {
        assertEquals(0, repository.merge(null));
    }

    @Test
    void persistTestAndThrowException() {
        assertEquals(0, repository.persist(null));
    }

    @Test
    void removeTestAndThrowException() {
        assertEquals(0, repository.remove(null));
    }
}
