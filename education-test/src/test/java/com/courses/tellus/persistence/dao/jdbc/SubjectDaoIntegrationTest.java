package com.courses.tellus.persistence.dao.jdbc;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.entity.model.Subject;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SubjectDaoIntegrationTest {

    private static SubjectDao subjectDao;
    private Subject subject;

    @BeforeEach
    void initializeSubject() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("src/test/resources/initial/h2/table/jdbc/subject_test_table.sql"));
        subjectDao = new SubjectDao(ConnectionFactory.getInstance());
        subject = new Subject(1L,"Biology", "Lessons about building of humans", true,
                LocalDate.of(1996,5, 12));
        subjectDao.insert(subject);
    }

    @Test
    void testGetAllAndReturnEntityList() throws Exception {
        List<Subject> subjectList = subjectDao.getAll();
        Assertions.assertEquals(1,subjectList.size());
    }

    @Test
    void testGetAllEntityAndReturnEmptyList() throws Exception {
        subjectDao.delete(subjectDao.getAll().get(0).getSubjectId());
        List<Subject> subjectList = subjectDao.getAll();
        Assertions.assertEquals(0, subjectList.size());
    }

    @Test
    void testGetEntityByIdAndReturnEntity() throws Exception {
        Optional<Subject> subject = subjectDao.getById(subjectDao.getAll().get(0).getSubjectId());
        assertTrue(subject.isPresent());
    }

    @Test
    void testGetEntityByIdAndReturnFalse() throws Exception {
        Optional<Subject> subject = subjectDao.getById(12L);
        Assertions.assertFalse(subject.isPresent());
    }

    @Test
    void testUpdateSubject() throws Exception {
        Subject subject = new Subject(1L, "Biology", "Lessons about building of humans",
                true, LocalDate.of(1996,5, 12));
        Assertions.assertEquals(1, subjectDao.update(subject));
    }

    @Test
    void testDeleteSubject() throws Exception {
        Assertions.assertEquals(1, subjectDao.delete(1L));
    }

    @Test
    void testInsertSubject() throws Exception {
        Assertions.assertEquals(1, subjectDao.insert(subject));
    }
}
