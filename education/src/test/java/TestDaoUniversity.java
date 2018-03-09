import com.courses.tellus.dao.UniversityDao;
import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.University;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.List;

public class TestDaoUniversity {

    private UniversityDao universityDao;
    private University university;

    @BeforeAll
    static void before() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(),
                new FileReader("/src/test/resourses/testTable.sql"));
    }

    @BeforeEach
    void beforeEach() {
        universityDao = new UniversityDao(ConnectionFactory.getInstance());
        university = new University(1L,"KPI","pr.Peremohy","Technical");
        universityDao.create(university);
    }

    @Test
    public void testGetAllObjects() {
        List<University> subjectList = universityDao.getAll();
        Assertions.assertTrue(!(subjectList.size() == 0));
    }

    @Test
    public void testGetEntityByIdWhenReturnEntity() {
        University university = universityDao.getEntityById(1L);
        Assertions.assertTrue(university != null);
    }

    @Test
    public void testGetEntityByIdWhenReturnNull() {
        University university = universityDao.getEntityById(1L);
        Assertions.assertTrue(university == null);
    }

    @Test
    public void testUpdateUniversity() {
        university.setNameOfUniversity("Sheva");
        boolean result = universityDao.update(university);
        Assertions.assertTrue(result);
    }

    @Test
    public void testDeleteUniversity() {
        boolean result = universityDao.delete(1L);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCreateUniversity() {
        boolean result = universityDao.create(university);
        Assertions.assertTrue(result);
    }
}
