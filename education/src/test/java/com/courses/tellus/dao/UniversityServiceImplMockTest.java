package com.courses.tellus.dao;

import com.courses.tellus.model.University;
import com.courses.tellus.service.UniversityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyLong;


public class UniversityServiceImplMockTest {

    @Mock
    private UniversityDao universityDao;

    @Mock
    private University university;

    @InjectMocks
    private UniversityServiceImpl universityServiceImpl;

    @BeforeEach
     void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testInsertUniversity() {
        assertEquals(universityServiceImpl.insert(university), universityDao.insert(university));
        }

        @Test
    void testUpdateUniversity() {
        assertEquals(universityServiceImpl.update(university),universityDao.update(university));
        }

        @Test
    void testDeleteUniversity(){
        assertEquals(universityServiceImpl.delete(anyLong()),universityDao.delete(anyLong()));

        }

        @Test
    void testGetById(){
        universityDao.getById(1L);
        assertEquals(universityServiceImpl.getById(anyLong()),universityDao.getById(anyLong()));
        }

        @Test
    void testGetAll(){
        assertEquals(universityServiceImpl.getAll(),universityDao.getAll());
        }
}
