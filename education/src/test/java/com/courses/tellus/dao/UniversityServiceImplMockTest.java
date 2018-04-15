package com.courses.tellus.dao;

import com.courses.tellus.model.University;
import com.courses.tellus.service.UniversityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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
        assertEquals(0, universityServiceImpl.insert(university));
        verify(universityDao, atLeastOnce()).insert(any());
        }

        @Test
    void testUpdateUniversity() {
            assertEquals(0, universityServiceImpl.update(university));
            verify(universityDao, atLeastOnce()).update(any());
        }

        @Test
    void testDeleteUniversity(){
        assertEquals(0,universityServiceImpl.delete(anyLong()));
        verify(universityDao, atLeastOnce()).delete(anyLong());

        }

        @Test
    void testGetById(){
            given(universityServiceImpl.getById(anyLong())).willReturn(Optional.of(university));
            assertTrue(universityServiceImpl.getById(anyLong()).isPresent());
            verify(universityDao, atLeastOnce()).getById(anyLong());
        }

        @Test
    void testGetAll(){
            List<University> universities = new ArrayList<>();
            universities.add(university);
            given(universityServiceImpl.getAll()).willReturn(universities);

            assertEquals(1, universityServiceImpl.getAll().size());
            verify(universityDao, atLeastOnce()).getAll();
        }
}
