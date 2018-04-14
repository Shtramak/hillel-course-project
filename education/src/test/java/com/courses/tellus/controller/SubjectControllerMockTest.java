package com.courses.tellus.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.courses.tellus.dto.SubjectDTO;
import com.courses.tellus.model.Subject;
import com.courses.tellus.service.SubjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SubjectControllerMockTest {

    @Mock private SubjectServiceImpl service;
    @Mock private SubjectDTO subjectDTO;
    @InjectMocks private SubjectController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllEntityGetRequestTest() {
        given(service.getAll()).willReturn(new ArrayList<>());

        assertEquals("subject_list", controller.getAllEntity().getViewName());
        verify(service, atLeastOnce()).getAll();
    }

    @Test
    void insertEntityGetRequestTest() {
        assertEquals("subject_create", controller.insertEntity().getViewName());
    }

    @Test
    void insertEntityPostRequestTest() {
        given(service.insert(subjectDTO)).willReturn(1);

        assertEquals("redirect:/subject", controller.insertEntity(subjectDTO).getViewName());
    }

    @Test
    void deleteEntityGetRequestTest() {
        given(service.delete(anyLong())).willReturn(1);

        assertEquals("redirect:/subject", controller.deleteEntity(anyLong()).getViewName());
    }

    @Test
    void updateEntityGetRequestAndIsPresentTest() {
        Subject subject = mock(Subject.class);
        given(service.getById(anyLong())).willReturn(Optional.of(subject));

        assertEquals("subject_edit", controller.updateEntity(anyLong()).getViewName());
    }

    @Test
    void updateEntityGetRequestAndIsAbsentTest() {
        given(service.getById(anyLong())).willReturn(Optional.empty());

        assertEquals("redirect:/subject", controller.updateEntity(anyLong()).getViewName());
    }

    @Test
    void updateEntityPostRequestTest() {
        given(service.update(subjectDTO)).willReturn(1);

        assertEquals("redirect:/subject", controller.updateEntity(subjectDTO).getViewName());
    }
}
