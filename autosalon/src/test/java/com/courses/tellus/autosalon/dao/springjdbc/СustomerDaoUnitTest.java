package com.courses.tellus.autosalon.dao.springjdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.model.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

public class СustomerDaoUnitTest {
    private static final Customer REAL_CUSTOMER =
            new Customer(1, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);

    @Mock
    private static JdbcTemplate jdbcTemplate;
    private CustomerDao customerDao;

    @BeforeAll
    static void initJdbcTemplate() {
        JdbcTemplatesConfig config = mock(JdbcTemplatesConfig.class);
        DataSource dataSource = mock(DataSource.class);
        when(config.dataSource()).thenReturn(dataSource);
        when(config.jdbcTemplate(dataSource)).thenReturn(jdbcTemplate);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerDao = new CustomerDao(jdbcTemplate);
    }

    @Test
    void getAllReturnsListofCustomers() {
        List<Customer> customers = Arrays.asList(REAL_CUSTOMER);
        when(jdbcTemplate.query(anyString(), any(CustomerMapper.class))).thenReturn(customers);
        assertEquals(customers, customerDao.getAll());
    }

    @Test
    void getByIdReturnsCustomerWithCpecifiedId1() throws Exception {
        when(jdbcTemplate.queryForObject(anyString(), any(CustomerMapper.class))).thenReturn(REAL_CUSTOMER);
        assertEquals(Optional.of(REAL_CUSTOMER), customerDao.getById(REAL_CUSTOMER.getId()));
    }

    @Test
    void updateReturnsNumberOfAffectedRows() {
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(0);
        assertEquals(Integer.valueOf(0), customerDao.update(REAL_CUSTOMER));
    }

    @Test
    void deleteReturnsNumberOfAffectedRows() {
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(0);
        assertEquals(Integer.valueOf(0), customerDao.delete(REAL_CUSTOMER.getId()));
    }

    @Test
    void insertReturnsNumberOfAffectedRows() {
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(0);
        assertEquals(Integer.valueOf(0), customerDao.insert(REAL_CUSTOMER));
    }
}
