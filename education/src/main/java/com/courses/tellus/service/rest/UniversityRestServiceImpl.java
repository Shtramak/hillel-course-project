package com.courses.tellus.service.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.dto.UniversityDto;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.persistence.repository.rest.UniversityRepository;
import org.springframework.stereotype.Service;

@Service
public class UniversityRestServiceImpl implements UniversityRestService<University, UniversityDto> {

    private final transient UniversityRepository repository;

    public UniversityRestServiceImpl(final UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<University> getAll() {
        final List<University> universities = new ArrayList<>();
        final Iterator<University> iterator = repository.findAll().iterator();
        if (iterator.hasNext()) {
            universities.add(iterator.next());
        }
        return universities;
    }

    @Override
    public Optional<University> getById(final Long uniId) {
        return repository.findById(uniId);
    }

    @Override
    public void insert(final UniversityDto universityDto) {
            final University university = new University();
            university.setNameOfUniversity(universityDto.getNameOfUniversity());
            university.setAddress(universityDto.getAddress());
            university.setSpecialization(universityDto.getSpecialization());
        repository.save(university);
    }

    @Override
    public void delete(final Long uniId) {
        repository.deleteById(uniId);

    }

    @Override
    public void update(final UniversityDto universityDto) {
        final University university = new University();
        university.setUniId(Long.parseLong(universityDto.getUniId()));
        university.setNameOfUniversity(universityDto.getNameOfUniversity());
        university.setAddress(universityDto.getAddress());
        university.setSpecialization(universityDto.getSpecialization());

        repository.save(university);
    }
}
