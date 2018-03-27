package com.courses.tellus.autosalon.servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JdbcTemplatesConfig.class})
@ExtendWith(SpringExtension.class)
public class CustomerServletUnitTest extends HttpServlet {
    private static final Customer EXISTED_CUSTOMER =
            new Customer(1, "John", "Rambo", LocalDate.of(1946, 07, 06), "(012)345-67-89", 10000000.50);

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    private CustomerServlet servlet;

    @BeforeEach
    @Sql("classpath:test.sql")
    void setUp() {
        MockitoAnnotations.initMocks(this);
        servlet = new CustomerServlet();
    }

    @Test
    void doGetddPath() throws Exception {
        when(request.getPathInfo()).thenReturn("/add");
        when(request.getRequestDispatcher("/WEB-INF/jsp/addCustomer.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void doGetListPath() throws Exception {
        when(request.getPathInfo()).thenReturn("/list");
        when(request.getRequestDispatcher("/WEB-INF/jsp/listCustomers.jsp")).thenReturn(dispatcher);
        ArgumentCaptor<List> customersCaptor = ArgumentCaptor.forClass(List.class);
        servlet.doGet(request, response);
        verify(request).setAttribute(anyString(), customersCaptor.capture());
        List<Customer> customers = Collections.singletonList(EXISTED_CUSTOMER);
        assertEquals(customersCaptor.getValue(), customers);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void doGetDigitPathWhenIdExists() throws Exception {
        when(request.getPathInfo()).thenReturn("/1");
        when(request.getRequestDispatcher("/WEB-INF/jsp/customerById.jsp")).thenReturn(dispatcher);
        ArgumentCaptor<Object> attributesCaptor = ArgumentCaptor.forClass(Object.class);
        servlet.doGet(request, response);
        verify(request, times(2)).setAttribute(anyString(), attributesCaptor.capture());
        verify(dispatcher).forward(request, response);
        List<Object> expected = Arrays.asList(1L, EXISTED_CUSTOMER);
        assertEquals(attributesCaptor.getAllValues(), expected);

    }

    @Test
    void doGetDigitPathWhenIdNotExists() throws Exception {
        when(request.getPathInfo()).thenReturn("/2");
        when(request.getRequestDispatcher("/WEB-INF/jsp/customerNotFound.jsp")).thenReturn(dispatcher);
        ArgumentCaptor<Long> customerIdCaptor = ArgumentCaptor.forClass(Long.class);
        servlet.doGet(request, response);
        verify(request).setAttribute(anyString(), customerIdCaptor.capture());
        verify(dispatcher).forward(request, response);
        Long expectedId = 2L;
        assertEquals(customerIdCaptor.getValue(), expectedId);
    }

    @Test
    void doPostAddCustomer() throws Exception {
        Customer customer = new Customer(2, "John", "Travolta", LocalDate.of(1954, 2, 18), "(012)345-67-89", 500000.50);
        when(request.getRequestDispatcher("/WEB-INF/jsp/successfulAdd.jsp")).thenReturn(dispatcher);
        when(request.getParameter("id")).thenReturn(String.valueOf(customer.getId()));
        when(request.getParameter("name")).thenReturn(customer.getName());
        when(request.getParameter("surname")).thenReturn(customer.getSurname());
        when(request.getParameter("phone")).thenReturn(customer.getPhoneNumber());
        when(request.getParameter("birthday")).thenReturn(String.valueOf(customer.getDateOfBirth()));
        when(request.getParameter("funds")).thenReturn(String.valueOf(customer.getAvailableFunds()));
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        servlet.doPost(request, response);
        verify(request).setAttribute(anyString(), customerCaptor.capture());
        verify(dispatcher).forward(request, response);
        assertEquals(customerCaptor.getValue(), customer);
    }
}