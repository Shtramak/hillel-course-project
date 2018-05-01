package com.courses.tellus.service.rest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        return subjectRepository.findById(entityId);
    }

    @Override
    public boolean insert(final SubjectDTO entity) {
        subjectRepository.save(parseEntity(entity));
        return true;
    }

    @Override
    public boolean delete(final Long entityId) {
        subjectRepository.deleteById(entityId);
        return true;
    }

    @Override
    public boolean update(final Long entityId, final SubjectDTO entity) {
        if (getEntityById(entityId).isPresent()) {
            entity.setSubjectId(entityId.toString());
            subjectRepository.save(parseEntity(entity));
            return true;
        } else {
            return false;
        }
    }

    private Subject parseEntity(final SubjectDTO subjectDTO) throws DateTimeParseException {
        final Subject subject = new Subject();
        if (subjectDTO.getSubjectId() != null) {
            subject.setSubjectId(Long.parseLong(subjectDTO.getSubjectId()));
        }
        subject.setName(subjectDTO.getName());
        subject.setDescription(subjectDTO.getDescription());
        subject.setValid("true".equals(subjectDTO.getValid()));
        final LocalDate date = LocalDate.parse(subjectDTO.getDateOfCreation());
        subject.setDateOfCreation(date);
        subject.setStudents(subjectDTO.getStudents());
        subject.setUniversity(subjectDTO.getUniversity());
        return subject;
    }
}
