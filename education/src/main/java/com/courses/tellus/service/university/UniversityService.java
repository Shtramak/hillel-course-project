package com.courses.tellus.service.university;


import com.courses.tellus.dao.spring.jdbc.UniversityDao;
import com.courses.tellus.entity.University;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("com.courses.tellus.dao.spring.jdbc")
public class UniversityService {

    @Autowired
    private transient UniversityDao universityDao;

    public Integer insert(final University university){
        return universityDao.insert(university);
    }

    public List<University> getAll(){
        return  universityDao.getAll();
    }

    public Optional<University> getById(final Long uniId){
        return universityDao.getById(uniId);
    }

    public Integer delete(final Long uniId){
        return universityDao.delete(uniId);
        }

    public Integer update(final University university){
        return universityDao.update(university);
    }
}
