package com.courses.tellus.dao.impl;

import com.courses.tellus.dao.StudentDao;
import com.courses.tellus.entity.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Student.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public Student getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Student) session.get(Student.class, id);
    }

    @Override
    public Student save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        return (Student) session.merge(student);
    }

    @Override
    public void update(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.update(student);
    }

    @Override
    public void delete(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(student);
    }
}
