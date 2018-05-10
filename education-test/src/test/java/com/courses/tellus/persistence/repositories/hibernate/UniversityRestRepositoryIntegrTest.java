package com.courses.tellus.persistence.repositories.hibernate;

import com.courses.tellus.config.hibernate.HibernateUtils;
import com.courses.tellus.entity.model.Student;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.repository.hibernate.UniversityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UniversityRestRepositoryIntegrTest {

    private UniversityRepository universityRepository;

    @BeforeEach
    void setup() {
        EntityManager manager = HibernateUtils.getManagerFactory().createEntityManager();
        universityRepository = new UniversityRepository(manager);
    }


    @Test
    void getByIdWhenReturnUniversity(){
        assertTrue(universityRepository.getById(4L).isPresent());
    }

    @Test
    void getByIdWhenEntityNotFound(){
        assertFalse(universityRepository.getById(null).isPresent());
    }

    @Test
    void getAllTestWhenReturnList() {
        assertFalse(universityRepository.getAll().isEmpty());
    }

    @Test
    void insertTestWhenSuccessful(){
        Set<Subject> subjects = new HashSet<>();
        Subject subject = new Subject (6L, "Math", "Science about count",
                true, LocalDate.of(2000,5,12));
        subjects.add(subject);

        Set<Student> students = new HashSet<>();
        Student student = new Student(6L, "John", "Shepard", "Lvivska 4 str",
                "423541646");
        students.add(student);

        University university = new University("testIn", "testIn", "testIn");
        university.setSubjects(subjects);
        university.setStudents(students);
        assertEquals(1, universityRepository.insert(university));
    }

    @Test
    void insertTestWhenThrowsException(){
        assertEquals(0,universityRepository.insert(null));
    }

    @Test
    void updateTestWhenSuccessful(){
        University university = new University(3L,"testUp", "testUp", "testUp");
        assertEquals(1,universityRepository.update(university));
        }

        @Test
    void updateTestWhenThrowsException(){
       assertEquals(0,universityRepository.update(null));
        }

        @Test
    void deleteTestWhenEntityIsDeleted(){
        assertEquals(1,universityRepository.delete(2L));
        }

        @Test
    void deleteTestWhenThrowsException(){
        assertEquals(0,universityRepository.delete(null));
        }
}
