package com.courses.tellus.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.courses.tellus.dao.UniversityDao;
import com.courses.tellus.model.University;

@Service
@ComponentScan("com.courses.tellus.dao.spring.jdbc")
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private transient UniversityDao universityDao;

    public int insert(final University university){
        return universityDao.insert(university);
    }

    public List<University> getAll(){
        return  universityDao.getAll();
    }

    public Optional<University> getById(final Long uniId){
        return universityDao.getById(uniId);
    }

    public int delete(final Long uniId){
        return universityDao.delete(uniId);
        }

    public int update(final University university){
        return universityDao.update(university);
    }
}
