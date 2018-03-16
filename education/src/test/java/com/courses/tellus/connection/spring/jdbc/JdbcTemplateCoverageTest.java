package com.courses.tellus.connection.spring.jdbc;

import java.util.GregorianCalendar;

import com.courses.tellus.dao.spring.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("prod")
@ExtendWith(SpringExtension.class)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "classpath:postgres/table/subject_test_table.sql")

@ContextConfiguration(classes = {JDBCTemplateConfiguration.class, SubjectDao.class})
class JdbcTemplateCoverageTest {

    @Autowired
    private SubjectDao subjectDao;
    private Subject subject;

    @BeforeEach
    void init() {
        subject = new Subject(1L,"Math", "Teach how calculate nums", true,
                new GregorianCalendar(200,5, 15));
        subjectDao.insert(subject);
    }

    @Test
    void testGetAllAndReturnEntityList() {
        Assertions.assertEquals(1, subjectDao.getAll().get().size());
    }
}
