package com.courses.tellus.service.rest;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.dto.SubjectDTO;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.persistence.repository.rest.SubjectRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectRestServiceImpl implements RestService<Subject, SubjectDTO> {

    private final transient SubjectRestRepository subjectRepository;

    @Autowired
    public SubjectRestServiceImpl(final SubjectRestRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> getEntityById(final Long entityId) {
        return Optional.of(subjectRepository.getOne(entityId));
    }

    @Override
    public boolean insert(final SubjectDTO entity) {

        return true;
    }

    @Override
    public boolean delete(final Long entityId) {
        return false;
    }

    @Override
    public boolean update(final SubjectDTO entity) {
        return false;
    }

    private Subject parseEntity(final SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        return subject;
    }
}
