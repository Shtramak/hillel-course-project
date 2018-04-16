package com.courses.tellus.service;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.dao.spring.jdbc.UniversityDao;
import com.courses.tellus.model.University;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("com.courses.tellus.dao.spring.jdbc")
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private transient UniversityDao universityDao;

    @Override
    public int insert(final University university) {
        return universityDao.insert(university);
    }

    @Override
    public List<University> getAll() {
        return  universityDao.getAll();
    }

    @Override
    public Optional<University> getById(final Long uniId) {
        return universityDao.getById(uniId);
    }

    @Override
    public int delete(final Long uniId) {
        return universityDao.delete(uniId);
        }

        @Override
    public int update(final University university) {
        return universityDao.update(university);
    }
}
