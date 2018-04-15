package com.courses.tellus.autosalon.dao.springjdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.courses.tellus.autosalon.model.Customer;
import org.junit.jupiter.api.Test;

public class CustomerMapperTest {
    private static final Customer REAL_CUSTOMER =
            new Customer(1, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);

    @Test
    void customerMapperMapRowReturnsCustomer() throws SQLException {
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getLong("id")).thenReturn(REAL_CUSTOMER.getId());
        when(resultSetMock.getString("name")).thenReturn(REAL_CUSTOMER.getName());
        when(resultSetMock.getString("surname")).thenReturn(REAL_CUSTOMER.getSurname());
        Date date = Date.valueOf(REAL_CUSTOMER.getDateOfBirth());
        when(resultSetMock.getDate("date_of_birth")).thenReturn(date);
        when(resultSetMock.getString("phone_number")).thenReturn(REAL_CUSTOMER.getPhoneNumber());
        when(resultSetMock.getDouble("available_fund")).thenReturn(REAL_CUSTOMER.getAvailableFunds());
        CustomerMapper customerMapper = new CustomerMapper();
        assertEquals(REAL_CUSTOMER, customerMapper.mapRow(resultSetMock, 1));
    }
}