import com.courses.tellus.dao.UniversityDao;
import com.courses.tellus.dbconnection.ConnectionFactory;
import com.courses.tellus.entity.University;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
public class UniversityDaoMock {

    private static UniversityDao universityDao;
    private static University university;
    private ConnectionFactory mockConnectionFactory;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;


    @BeforeEach
    public void reInitUniversityDao() throws SQLException {
        mockConnectionFactory = mock(ConnectionFactory.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock (ResultSet.class);
        universityDao = new UniversityDao(mockConnectionFactory);
        university = new University(1, "KPI", "pr.Peremohy",
                "Technical");
    }

    @Test
    public void testCreateUniversityDao() throws Exception {
        when(mockConnectionFactory.getConnection().prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        boolean result = universityDao.create(university);
        Assertions.assertTrue(result);
    }

    @Test
    public void testGetUniversityById() throws Exception {
        when(mockConnectionFactory.getConnection().prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.getResultSet()).thenReturn(mockResultSet);
        int IdForGetting = mockResultSet.getInt(anyString());
        Assertions.assertNotEquals(IdForGetting, university.getUniId());

    }

    @Test
    public void testGetAllUniversities() throws SQLException {
        List<University> list = new ArrayList<University>();
        List<University> spy = spy(list);
        when(mockConnectionFactory.getConnection().prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.getResultSet()).thenReturn(mockResultSet);
        while(mockResultSet.next()) {
            university.setUniId(mockResultSet.getInt("id"));
            university.setNameOfUniversity(mockResultSet.getString("nameOfUniversity"));
            university.setAddress(mockResultSet.getString("address"));
            spy.add(university);
        }
        Assertions.assertEquals(mockResultSet.getType(), spy.size());
    }

    @Test
    public void testDeleteUniversityById() throws Exception {
        when(mockConnectionFactory.getConnection().prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        Assertions.assertTrue(universityDao.create(university));
    }

    @Test
    public void testSqlExceptionCreateUniversity() throws SQLException {
        when(mockConnectionFactory.getConnection().prepareStatement(anyString())).thenThrow(SQLException.class);
        Assertions.assertEquals(false, universityDao.create(university));
    }

    @Test
    public void testSqlExceptionGetUniversityById() throws SQLException {
        when(mockConnectionFactory.getConnection().prepareStatement(anyString())).thenThrow(SQLException.class);
        Assertions.assertEquals(null, universityDao.getEntityById(0));
    }

    @Test
    public void testSqlExceptionGetAllUniversities() throws SQLException {
        when(mockConnectionFactory.getConnection().prepareStatement(anyString())).thenThrow(SQLException.class);
        Assertions.assertEquals(null, universityDao.getAllObject());
    }

    @Test
    public void testSqlExceptionDeleteUniversity() throws SQLException {
        when(mockConnectionFactory.getConnection().prepareStatement(anyString())).thenThrow(SQLException.class);
        Assertions.assertEquals(false, universityDao.delete(0));
    }
}

