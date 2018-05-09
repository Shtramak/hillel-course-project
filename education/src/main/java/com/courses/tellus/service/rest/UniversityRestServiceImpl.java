package com.courses.tellus.service.rest;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.dto.UniversityDto;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.repository.rest.UniversityRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityRestServiceImpl implements RestService<University, UniversityDto> {

    private final transient UniversityRestRepository repository;

    @Autowired
    public UniversityRestServiceImpl(final UniversityRestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<University> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<University> getEntityById(final Long entityId) {
        return repository.findById(entityId);
    }

    @Override
    public boolean insert(final UniversityDto entity) {
        repository.save(parseEntity(entity));
        return true;
    }

    @Override
    public boolean delete(final Long entityId) {
        repository.deleteById(entityId);
        return true;
    }

    @Override
    public boolean update(final Long entityId, final UniversityDto entity) {
        if (getEntityById(entityId).isPresent()) {
            entity.setUniId(entityId.toString());
            repository.save(parseEntity(entity));
            return true;
        } else {
            return false;
        }
    }

    private University parseEntity(final UniversityDto universityDto) {
        final University university = new University();
        if (universityDto.getUniId() != null) {
            university.setUniId(Long.parseLong(universityDto.getUniId()));
        }
        university.setNameOfUniversity(universityDto.getNameOfUniversity());
        university.setAddress(universityDto.getAddress());
        university.setSpecialization(universityDto.getSpecialization());
        university.setStudents(universityDto.getStudents());
        university.setSubjects(universityDto.getSubjects());
        return university;
    }
}
