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

    @BeforeAll
    static void prepareConnection() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/subject_db_table.sql"));
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
    }

    @BeforeEach
    void initializeSubject() {
        subject = new Subject("Biology", "Lessons about building of humans", true,
                new GregorianCalendar(1996,5,12));
        subjectDao.insert(subject);
    }

    @AfterEach
    void clean() {
        subjectDao.delete(1L);
        subjectDao.delete(2L);
        subjectDao.delete(3L);
        subjectDao.delete(4L);
        subjectDao.delete(5L);
    }

    @Test
    void testGetAllObjectAndReturnObjectList() throws Exception {
        List<Subject> subjectList = subjectDao.getAllEntity();
        Assertions.assertEquals(1, subjectList.size());
    }

    @Test
    void testGetAllObjectAndReturnEmptyList() throws Exception {
        subjectDao.delete(subjectDao.getAllEntity().get(0).getSubjectId());
        List<Subject> subjectList = subjectDao.getAllEntity();
        Assertions.assertEquals(0, subjectList.size());
    }

    @Test
    void testGetEntityByIdAndReturnEntity() throws Exception {
        Subject subject = subjectDao.getEntityById(subjectDao.getAllEntity().get(0).getSubjectId());
        Assertions.assertNotNull(subject);
    }

    @Test
    void testGetEntityByIdAndReturnNull() throws Exception {
        Subject subject = subjectDao.getEntityById(12L);
        Assertions.assertNull(subject);
    }

    @Test
    void testUpdateSubject() throws Exception {
        Subject subject = new Subject(1L, "Biology", "Lessons about building of humans", true,
                new GregorianCalendar(2000,5,12));
        Assertions.assertTrue(subjectDao.update(subject));
    }

    @Test
    void testDeleteSubject() {
        Assertions.assertTrue(subjectDao.delete(1L));
    }

    @Test
    void testInsertSubject() {
        Assertions.assertTrue(subjectDao.insert(subject));
    }
}
