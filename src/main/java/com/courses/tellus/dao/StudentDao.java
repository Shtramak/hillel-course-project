package com.courses.tellus.dao;

import com.courses.tellus.entity.Student;

import java.util.List;

public interface StudentDao {

    List<Student> getAll();

    Student getById(long id);

    Student save(Student student);

    void update(Student student);

    void delete(Student student);
}
