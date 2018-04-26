package com.courses.tellus.persistence.repositories.hibernate;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.courses.tellus.config.hibernate.HibernateUtils;
import com.courses.tellus.entity.model.Student;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.repository.hibernate.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepoIntegrationTest {

    private StudentRepository repository;

    @BeforeEach
    void setup() {
        EntityManager manager = HibernateUtils.getManagerFactory().createEntityManager();
        repository = new StudentRepository(manager);
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
        Student DATABASE_STUDENT = new Student(3L,"testUp", "testUp", "testUp", "testUp");
        assertEquals(1, repository.update(DATABASE_STUDENT));
    }

    @Test
    void persistTestAndReturnSuccessfulValue() {
        Set<University> universities = new HashSet<>();
        University university = new University(5L, "KPI", "peremohy 30 str.", "technical");
        universities.add(university);

        Set<Subject> subjects = new HashSet<>();
        Subject subject = new Subject(5L, "Math", "Science about count", true, LocalDate.of(2000, 5, 12));
        subjects.add(subject);

        Student student = new Student("testIn", "testIn", "testIn", "testIn");
        student.setUniversities(universities);
        student.setSubjects(subjects);
        assertEquals(1, repository.insert(student));
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