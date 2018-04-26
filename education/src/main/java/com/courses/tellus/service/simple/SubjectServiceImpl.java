package com.courses.tellus.service.simple;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.entity.dto.SubjectDTO;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.persistence.dao.spring.SubjectDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private static final Logger LOGGER = Logger.getLogger(SubjectServiceImpl.class);
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
        try {
            final Subject subject = new Subject();
            subject.setName(subjectDTO.getName());
            subject.setDescription(subjectDTO.getDescription());
            subject.setValid("true".equals(subjectDTO.getValid()));
            final LocalDate date = LocalDate.parse(subjectDTO.getDateOfCreation());
            subject.setDateOfCreation(date);
            return subjectDao.insert(subject);
        } catch (NumberFormatException | DateTimeException exception) {
            LOGGER.error(exception.getCause(), exception);
            return 0;
        }
    }

    @Override
    public int delete(final Long subjectId) {
        return subjectDao.delete(subjectId);
    }

    @Override
    public int update(final SubjectDTO subjectDTO) {
        try {
            final Subject subject = new Subject();
            subject.setSubjectId(Long.parseLong(subjectDTO.getSubjectId()));
            subject.setName(subjectDTO.getName());
            subject.setDescription(subjectDTO.getDescription());
            subject.setValid("true".equals(subjectDTO.getValid()));
            final LocalDate date = LocalDate.parse(subjectDTO.getDateOfCreation());
            subject.setDateOfCreation(date);
            return subjectDao.update(subject);
        } catch (NumberFormatException | DateTimeException exception) {
            LOGGER.error(exception.getCause(), exception);
            return 0;
        }
    }
}
