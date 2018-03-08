import com.courses.tellus.dao.UniversityDao;
import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.University;
import org.h2.tools.RunScript;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.List;

public class TestDaoUniversity {
    private UniversityDao universityDao = new UniversityDao(ConnectionFactory.getInstance());

    @BeforeAll
    public static void before() throws Exception {
        RunScript.execute(ConnectionFactory.getInstance().getConnection(), new FileReader("src/test/resourses/testTable.sql"));

    }


    @Test
    public void testCreateUniversity() {
        University university = new University(1,"Kpi","street",
                "technical");
        MatcherAssert.assertThat(universityDao.create(university), CoreMatchers.is(0));
    }

}
