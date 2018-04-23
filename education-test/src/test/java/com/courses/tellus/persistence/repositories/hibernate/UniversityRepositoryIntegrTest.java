package com.courses.tellus.persistence.repositories.hibernate;

import com.courses.tellus.config.hibernate.HibernateUtils;
import com.courses.tellus.entity.model.Student;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.repository.hibernate.UniversityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.h2.expression.Function.TO_DATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UniversityRepositoryIntegrTest {

    private UniversityRepository universityRepository;
    private University university;
    private Set<Student> students;
    private Set<Subject> subjects;
    private Student student;
    private Subject subject;



    @BeforeEach
    void setup() {
        EntityManager manager = HibernateUtils.getManagerFactory().createEntityManager();
        universityRepository = new UniversityRepository(manager);
        subjects =new HashSet<>();
        students = new HashSet<>();
        student = new Student(2L, "John", "Shepard", "Lvivska 3 str",
                "423541646" );
        subject = new Subject (2L, "Math", "Science about nums and count",
                true, LocalDate.of(2000,5,12));

        subjects.add(subject);
        students.add(student);

        university = new University("testName", "testAddress", "testSpecialization");
        university.setSubjects(subjects);
        university.setStudents(students);

    }


    @Test
    void getByIdWhenReturnUniversity(){
        assertTrue(universityRepository.getById(2L).isPresent());
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
        assertEquals(1,universityRepository.insert(university));
    }

    @Test
    void insertTestWhenThrowsException(){
        assertEquals(0,universityRepository.insert(null));
    }
    @Test
    void updateTestWhenSuccessful(){
        university.setAddress("testAddressUpdated");
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
