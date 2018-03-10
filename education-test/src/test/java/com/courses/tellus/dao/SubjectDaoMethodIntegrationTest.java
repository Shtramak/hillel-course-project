package com.courses.tellus.dao;

import java.io.FileReader;
import java.util.GregorianCalendar;
import java.util.List;

import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.Subject;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;

class SubjectDaoMethodIntegrationTest {

    private static SubjectDao subjectDao;
    private Subject subject;

    @BeforeEach
    void initializeSubject() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/subject_test_table.sql"));
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
        subject = new Subject("Biology", "Lessons about building of humans", true,
                new GregorianCalendar(1996,5,12));
        subjectDao.insert(subject);
    }

    @AfterEach
    void clearTable() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/trunc.sql"));
    }

    @Test
    void testGetAllAndReturnEntityList() throws Exception {
        List<Subject> subjectList = subjectDao.getAll();
        Assertions.assertEquals(1, subjectList.size());
    }

    @Test
    void testGetAllObjectAndReturnEmptyList() throws Exception {
        subjectDao.delete(subjectDao.getAll().get(0).getSubjectId());
        List<Subject> subjectList = subjectDao.getAll();
        Assertions.assertEquals(0, subjectList.size());
    }

    @Test
    void testGetEntityByIdAndReturnEntity() throws Exception {
        Subject subject = subjectDao.getById(subjectDao.getAll().get(0).getSubjectId());
        Assertions.assertNotNull(subject);
    }

    @Test
    void testGetEntityByIdAndReturnNull() throws Exception {
        Subject subject = subjectDao.getById(12L);
        Assertions.assertNull(subject);
    }

    @Test
    void testUpdateSubject() throws Exception {
        Subject subject = new Subject(1L, "Biology", "Lessons about building of humans", true,
                new GregorianCalendar(2000,5,12));
        Assertions.assertEquals(1, subjectDao.update(subject));
    }

    @Test
    void testDeleteSubject() {
        Assertions.assertEquals(1, subjectDao.delete(1L));
    }

    @Test
    void testInsertSubject() {
        Assertions.assertEquals(1, subjectDao.insert(subject));
    }
}
