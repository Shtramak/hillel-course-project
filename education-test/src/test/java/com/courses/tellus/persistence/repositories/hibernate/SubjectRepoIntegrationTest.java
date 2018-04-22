package com.courses.tellus.persistence.repositories.hibernate;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.courses.tellus.config.hibernate.HibernateUtils;
import com.courses.tellus.entity.model.Student;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.repository.hibernate.SubjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubjectRepoIntegrationTest {

    private SubjectRepository repository;
    private Set<Student> students;
    private University university;
    private Subject subject;

    @BeforeEach
    void setup() {
        EntityManager manager = HibernateUtils.getManagerFactory().createEntityManager();
        repository = new SubjectRepository(manager);

        university = new University(2L, "KPI", "peremohy 27 str.", "technical");

        students = new HashSet<>();
        students.add(new Student(2L, "John", "Shepard", "423541646", "Lvivska 3 str"));

        subject = new Subject("history", "fdsfs", true, LocalDate.of(2000, 5, 12));
        subject.setStudents(students);
        subject.setUniversity(university);

        if (repository.getAll().size() == 1) {
            repository.insert(subject);
        }
    }

    @AfterEach
    void clean() {
        while (repository.getAll().size() > 2) {
            final List<Subject> subjects = repository.getAll();
            repository.delete(subjects.get(subjects.size() - 1).getSubjectId());
        }
    }

    @Test
    void findAllTestAndReturnEntityList() {
        Subject subject = repository.getAll().get(1);
        assertEquals(2, repository.getAll().size());
        assertEquals(university, subject.getUniversity());
        assertEquals(students, subject.getStudents());
    }

    @Test
    void getByIdTestAndReturnEntity() {
        assertTrue(repository.getById(2L).isPresent());
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