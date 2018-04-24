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
    private Student student;
    private Set<University> universities;
    private Set<Subject> subjects;

    @BeforeEach
    void setup() {
        EntityManager manager = HibernateUtils.getManagerFactory().createEntityManager();
        repository = new StudentRepository(manager);

        universities = new HashSet<>();
        University university = new University(2L, "KPI", "peremohy 27 str.", "technical");
        universities.add(university);

        subjects = new HashSet<>();
        Subject subject = new Subject(2L, "history", "fdsfs", true, LocalDate.of(2000, 5, 12));
        subjects.add(subject);

        student = new Student("John", "Shepard", "423541646", "Lvivska 3 str");
        student.setUniversities(universities);
        student.setSubjects(subjects);
    }

    @Test
    void findAllTestAndReturnEntityList() {
        assertFalse(repository.getAll().isEmpty());
    }

    @Test
    void getByIdTestAndReturnEntity() {
        assertTrue(repository.getById(2L).isPresent());
    }

    @Test
    void mergeTestAndReturnSuccessfulValue() {
        student.setFirstName("George");
        assertEquals(1, repository.update(student));
    }

    @Test
    void persistTestAndReturnSuccessfulValue() {
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