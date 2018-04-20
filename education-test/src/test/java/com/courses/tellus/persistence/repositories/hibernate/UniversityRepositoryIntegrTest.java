package com.courses.tellus.persistence.repositories.hibernate;

import com.courses.tellus.config.hibernate.HibernateUtils;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.repository.hibernate.UniversityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UniversityRepositoryIntegrTest {

    private UniversityRepository universityRepository;
    private University university;

    @BeforeEach
    void setup() {
        EntityManager manager = HibernateUtils.getManagerFactory().createEntityManager();
        universityRepository = new UniversityRepository(manager);
        university = new University("testName", "testAddress", "testSpecialization");
        if (universityRepository.getAll().size() == 0) {
            universityRepository.insert(university);
        }
    }
    @AfterEach
    void clean(){
        while (universityRepository.getAll().size() > 1) {
            final List<University> universities = universityRepository.getAll();
            universityRepository.delete(universities.get(universities.size() - 1).getUniId());
        }
    }

    @Test
    void getAllTestWhenReturnList(){
        assertEquals(1,universityRepository.getAll().size());
    }

    @Test
    void getByIdWhenReturnUniversity(){
        assertTrue(universityRepository.getById(1L).isPresent());
    }

    @Test
    void getByIdWhenEntityNotFound(){
        assertFalse(universityRepository.getById(null).isPresent());
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
        assertEquals(1,universityRepository.delete(1L));
        }

        @Test
    void deleteTestWhenThrowsException(){
        assertEquals(0,universityRepository.delete(null));
        }
}
