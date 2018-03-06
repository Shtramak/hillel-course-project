package com.courses.tellus.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.Subject;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubjectDaoMethodsTest {

    private SubjectDao subjectDao;

    @BeforeAll
    static void before() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/subject_db_table.sql"));
    }

    @BeforeEach
    void beforeEach() {
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
        Subject subject1 = new Subject(
                1, "Biology", "Lessons about building of humans", true,
                new GregorianCalendar(1996,5,12));
        Subject subject2 = new Subject(
                2, "History", "Tell us about pass days", true,
                new GregorianCalendar(1996,5,12));
        subjectDao.insert(subject1);
        subjectDao.insert(subject2);

    }
    @Test
    public void testGetAllObjectAndReturnObjectList() {
        List<Subject> subjectList = subjectDao.getAllEntity();
        Assertions.assertTrue(!(subjectList.size() == 0));
    }

    @Test
    public void testGetEntityByIdAndReturnEntity() {
        Subject subject = subjectDao.getEntityById(2);
        Assertions.assertTrue(subject != null);
    }

    @Test
    public void testGetEntityByIdAndReturnNull() {
        Subject subject = subjectDao.getEntityById(1);
        Assertions.assertTrue(subject == null);
    }

    @Test
    public void testUpdateSubject() {
        Subject subject = new Subject(
                3, "Math", "Teach how to calculate numbers", true,
                new GregorianCalendar(1996,5,12));
        boolean result = subjectDao.update(subject);
        Assertions.assertTrue(result);
    }

    @Test
    public void testDeleteSubject() {
        boolean result = subjectDao.delete(1);
        Assertions.assertTrue(result);
    }

    @Test
    public void testInsertSubject() {
        Subject subject = new Subject(
                3, "Math", "Teach how to calculate numbers", true,
                new GregorianCalendar(1996,5,12));
        boolean result = subjectDao.insert(subject);
        Assertions.assertTrue(result);
    }
}
