package com.courses.tellus.persistence.repositories.hibernate;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.courses.tellus.config.hibernate.HibernateUtils;
import com.courses.tellus.entity.model.Student;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.repository.hibernate.SubjectRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubjectRepoIntegrationTest {

    private SubjectRepository repository;

    @BeforeEach
    void setup() {
        EntityManager manager = HibernateUtils.getManagerFactory().createEntityManager();
        repository = new SubjectRepository(manager);
    }

    @Test
    void findAllTestAndReturnEntityList() {
        assertFalse(repository.getAll().isEmpty());
    }

    @Test
    void getByIdTestAndReturnEntity() {
        assertTrue(repository.getById(4L).isPresent());
    }

    @Test
    void mergeTestAndReturnSuccessfulValue() {
        Subject DATABASE_SUBJECT = new Subject(3L,"testUp", "testUp", true,
                LocalDate.of(2000, 5, 12));
        assertEquals(1, repository.update(DATABASE_SUBJECT));
    }

    @Test
    void persistTestAndReturnSuccessfulValue() {
        University university = new University(4L, "KPI", "peremohy 30 str.", "technical");

        Set<Student> students = new HashSet<>();
        students.add(new Student(4L, "John", "Shepard", "423541646", "Lvivska 4 str"));

        Subject subject = new Subject("testIn", "testIn", true, LocalDate.of(2000, 5, 12));
        subject.setStudents(students);
        subject.setUniversity(university);
        assertEquals(1, repository.insert(subject));
    }

    @Test
    void removeTestAndReturnSuccessfulValue() {
        assertEquals(1, repository.delete(2L));
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