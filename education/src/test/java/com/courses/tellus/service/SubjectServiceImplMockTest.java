package com.courses.tellus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.courses.tellus.dao.SubjectDao;
import com.courses.tellus.dto.SubjectDTO;
import com.courses.tellus.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class SubjectServiceImplMockTest {

    @Mock private SubjectDao subjectDao;
    @Mock private Subject subject;
    @Mock private SubjectDTO subjectDTO;
    @InjectMocks private SubjectServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTest() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        given(subjectDao.getAll()).willReturn(subjects);

        assertEquals(1, service.getAll().size());
        verify(subjectDao, atLeastOnce()).getAll();
    }

    @Test
    void getByIdTest() {
        given(subjectDao.getById(anyLong())).willReturn(Optional.of(subject));

        assertTrue(service.getById(anyLong()).isPresent());
        verify(subjectDao, atLeastOnce()).getById(anyLong());
    }

    @Test
    void deleteTest() {
        given(subjectDao.delete(anyLong())).willReturn(1);

        assertEquals(1, service.delete(anyLong()));
        verify(subjectDao, atLeastOnce()).delete(anyLong());
    }

    @Test
    void insertTest() {
        given(subjectDTO.getName()).willReturn("Math");
        given(subjectDTO.getDescription()).willReturn("Leave me alone");
        given(subjectDTO.getValid()).willReturn("Y");
        given(subjectDTO.getDateOfCreation()).willReturn("2000-05-15");
        given(subjectDao.insert(any())).willReturn(1);

        assertEquals(1, service.insert(subjectDTO));
        verify(subjectDao, atLeastOnce()).insert(any());
    }

    @Test
    void updateTest() {
        given(subjectDTO.getSubjectId()).willReturn("1");
        given(subjectDTO.getName()).willReturn("Math");
        given(subjectDTO.getDescription()).willReturn("Leave me alone");
        given(subjectDTO.getValid()).willReturn("Y");
        given(subjectDTO.getDateOfCreation()).willReturn("2000-05-15");
        given(subjectDao.update(any())).willReturn(1);

        assertEquals(1, service.update(subjectDTO));
        verify(subjectDao, atLeastOnce()).update(any());
    }
}
