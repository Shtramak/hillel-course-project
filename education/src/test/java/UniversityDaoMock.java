import com.courses.tellus.dao.UniversityDao;
import com.courses.tellus.db_connection.ConnectionFactory;
import com.courses.tellus.entity.University;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UniversityDaoMock {
    private static ConnectionFactory connectionFactory;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private static UniversityDao universityDao;

    @BeforeAll
    public static void init() {
        connectionFactory = mock(ConnectionFactory.class);
        universityDao = new UniversityDao(connectionFactory);
    }

    @BeforeEach
    public void reInitDepartmentDao() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        when(connectionFactory.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testCreateUniversity() throws Exception {
        University university =new University();
        university.setId(1);
        university.setNameOfUniversity("KPI");
        university.setAddress("pr.Peremohy");
        university.setSpecialization("Technical");
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        int result = universityDao.create(university);
        Assertions.assertTrue(result == 0);
    }
}
