package com.courses.tellus.service.rest;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.dto.StudentDto;
import com.courses.tellus.entity.model.Student;
import com.courses.tellus.persistence.repository.rest.StudentRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRestServiceImpl implements RestService<Student, StudentDto> {

    private final transient StudentRestRepository repository;

    @Autowired
    public StudentRestServiceImpl(final StudentRestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Student> getEntityById(final Long entityId) {
        return repository.findById(entityId);
    }

    @Override
    public boolean insert(final StudentDto entity) {
        repository.save(parseEntity(entity));
        return true;
    }

    @Override
    public boolean delete(final Long entityId) {
        repository.deleteById(entityId);
        return true;
    }

    @Override
    public boolean update(final Long entityId, final StudentDto entity) {
        if (getEntityById(entityId).isPresent()) {
            entity.setStudentId(entityId.toString());
            repository.save(parseEntity(entity));
            return true;
        } else {
            return false;
        }
    }

    private Student parseEntity(final StudentDto studentDto) {
        final Student student = new Student();
        if (studentDto.getStudentId() != null) {
            student.setStudentId(Long.parseLong(studentDto.getStudentId()));
        }
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setStudentCardNumber(studentDto.getStudentCardNumber());
        student.setAddress(studentDto.getAddress());
        student.setUniversities(studentDto.getUniversities());
        student.setSubjects(studentDto.getSubjects());
        return student;
    }
}

