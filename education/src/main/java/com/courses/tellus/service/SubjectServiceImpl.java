package com.courses.tellus.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.dao.SubjectDao;
import com.courses.tellus.dto.SubjectDTO;
import com.courses.tellus.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final transient SubjectDao subjectDao;

    @Autowired
    public SubjectServiceImpl(final SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public List<Subject> getAll() {
        return subjectDao.getAll();
    }

    @Override
    public Optional<Subject> getById(final Long subjectId) {
        return subjectDao.getById(subjectId);
    }

    @Override
    public int insert(final SubjectDTO subjectDTO) {
        final Subject subject = new Subject();
        subject.setName(subjectDTO.getName());
        subject.setDescription(subjectDTO.getDescription());
        subject.setValid("true".equals(subjectDTO.getValid()));
        final LocalDate date = LocalDate.parse(subjectDTO.getDateOfCreation());
        subject.setDateOfCreation(date);
        return subjectDao.insert(subject);
    }

    @Override
    public int delete(final Long subjectId) {
        return subjectDao.delete(subjectId);
    }

    @Override
    public int update(final SubjectDTO subjectDTO) {
        final Subject subject = new Subject();
        subject.setSubjectId(Long.parseLong(subjectDTO.getSubjectId()));
        subject.setName(subjectDTO.getName());
        subject.setDescription(subjectDTO.getDescription());
        subject.setValid("true".equals(subjectDTO.getValid()));
        final LocalDate date = LocalDate.parse(subjectDTO.getDateOfCreation());
        subject.setDateOfCreation(date);
        return subjectDao.update(subject);
    }
}
