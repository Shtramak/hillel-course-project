package com.courses.tellus.dao.jdbc;

import java.io.FileReader;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.entity.Subject;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;

class SubjectDaoIntegrationTest {

    private static SubjectDao subjectDao;
    private Subject subject;

    @BeforeEach
    void initializeSubject() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/initial/h2/table/subject_test_table.sql"));
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
        subject = new Subject("Biology", "Lessons about building of humans", true,
                new GregorianCalendar(1996,5,12));
        subjectDao.insert(subject);
    }

    @AfterEach
    void clearTable() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/initial/h2/util/trunc.sql"));
    }

    @Test
    void testGetAllAndReturnEntityList() throws Exception {
        Optional<List<Subject>> subjectList = subjectDao.getAll();
        Assertions.assertTrue(subjectList.isPresent());
    }

    @Test
    void testGetAllEntityAndReturnEmptyList() throws Exception {
        subjectDao.delete(subjectDao.getAll().get().get(0).getSubjectId());
        Optional<List<Subject>> subjectList = subjectDao.getAll();
        Assertions.assertEquals(0, subjectList.get().size());
    }

    @Test
    void testGetEntityByIdAndReturnEntity() throws Exception {
        Optional<Subject> subject = subjectDao
                .getById(subjectDao.getAll().get().get(0).getSubjectId());
        Assertions.assertTrue(subject.isPresent());
    }

    @Test
    void testGetEntityByIdAndReturnFalse() throws Exception {
        Optional<Subject> subject = subjectDao.getById(12L);
        Assertions.assertFalse(subject.isPresent());
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
