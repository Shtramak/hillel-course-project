package com.courses.tellus.service.rest;

import com.courses.tellus.entity.dto.UniversityDto;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.repository.rest.UniversityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class UniversityRestServiceMockTest {

    @Mock
    private UniversityDto universityDto;

    @Mock
    private University university;

    @Mock
    private UniversityRepository repository;


    @InjectMocks
    private UniversityRestServiceImpl universityRestService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTest(){
        List<University> universities = new ArrayList<>();
        universities.add(university);
        given(universityRestService.getAll()).willReturn(universities);

        assertEquals(1, universityRestService.getAll().size());
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    void getById(){
        universityRestService.getById(anyLong());
        verify(repository,atLeastOnce()).findById(anyLong());
    }

    @Test
    void insert(){
        given(universityDto.getNameOfUniversity()).willReturn("testN");
        given(universityDto.getAddress()).willReturn("testA");
        given(universityDto.getSpecialization()).willReturn("testS");

        universityRestService.insert(universityDto);
        verify(repository, atLeastOnce()).save(any());
    }

    @Test
    void delete(){
        universityRestService.delete(anyLong());
        verify(repository, atLeastOnce()).deleteById(anyLong());

    }

    @Test
    void update(){
        given(universityDto.getUniId()).willReturn("1");
        given(universityDto.getNameOfUniversity()).willReturn("testN");
        given(universityDto.getAddress()).willReturn("testA");
        given(universityDto.getSpecialization()).willReturn("testS");

        universityRestService.update(universityDto);
        verify(repository, atLeastOnce()).save(any());

    }

}
