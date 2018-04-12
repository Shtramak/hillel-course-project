package com.courses.tellus.autosalon.servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.autosalon.dao.springjdbc.CustomerDao;
import com.courses.tellus.autosalon.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerHandlerUnitTest {
    private static final Customer REAL_CUSTOMER =
            new Customer(1, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private CustomerDao customerDao;
    @Mock
    private RequestDispatcher requestDispatcher;
    private MainServlet servlet;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new MainServlet();

        CustomerHandler customerHandler = new CustomerHandler();
        Field daoField = customerHandler.getClass().getDeclaredField("customerDao");
        daoField.setAccessible(true);
        daoField.set(customerHandler, customerDao);

        Field factory = HandlerFactory.class.getDeclaredField("handlerMap");
        factory.setAccessible(true);
        Map<String, InternalHandler> handlerMap = Collections.singletonMap("customer", customerHandler);
        factory.set(HandlerFactory.class, handlerMap);
    }

    @Test
    void getWhenPathIsAdd() throws Exception {
        when(request.getPathInfo()).thenReturn("/customer/add");
        when(request.getRequestDispatcher("/WEB-INF/jsp/addCustomer.jsp")).thenReturn(requestDispatcher);
        servlet.doGet(request, response);
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void getWhenPathIsList() throws Exception {
        when(request.getPathInfo()).thenReturn("/customer/list");
        List<Customer> customers = Collections.singletonList(REAL_CUSTOMER);
        when(customerDao.getAll()).thenReturn(customers);
        when(request.getRequestDispatcher("/WEB-INF/jsp/listCustomers.jsp")).thenReturn(requestDispatcher);
        ArgumentCaptor<String> captorName = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor captorParameter = ArgumentCaptor.forClass(List.class);
        servlet.doGet(request, response);
        verify(request, times(1)).setAttribute(captorName.capture(), captorParameter.capture());
        verify(requestDispatcher, times(1)).forward(request, response);
        assertEquals("customers", captorName.getValue());
        assertEquals(customers, captorParameter.getValue());
    }

    @Test
    void getWhenPathIsNumericAndCustomerExists() throws Exception {
        when(request.getPathInfo()).thenReturn("/customer/1");
        Long customerId = 1L;
        Optional<Customer> customer = Optional.of(REAL_CUSTOMER);
        when(customerDao.getById(customerId)).thenReturn(customer);
        when(request.getRequestDispatcher("/WEB-INF/jsp/customerById.jsp")).thenReturn(requestDispatcher);
        ArgumentCaptor<String> captorName = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Customer> captorParameter = ArgumentCaptor.forClass(Customer.class);
        servlet.doGet(request, response);
        verify(request, times(2)).setAttribute(captorName.capture(), captorParameter.capture());
        verify(requestDispatcher).forward(request, response);
        List<String> expectedNames = Arrays.asList("customerId", "customer");
        List<Object> expectedParameters = Arrays.asList(1L, customer.get());
        assertEquals(expectedNames, captorName.getAllValues());
        assertEquals(expectedParameters, captorParameter.getAllValues());
    }

    @Test
    void getWhenPathIsNumericAndCustomerNotExists() throws Exception {
        when(request.getPathInfo()).thenReturn("/customer/749");
        Optional<Customer> customer = Optional.empty();
        when(customerDao.getById(749L)).thenReturn(customer);
        when(request.getRequestDispatcher("/WEB-INF/jsp/customerNotFound.jsp")).thenReturn(requestDispatcher);
        servlet.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void postFromAddCustomerJsp() throws Exception {
        when(request.getPathInfo()).thenReturn("/customer/add");
        when(request.getParameter("id")).thenReturn(String.valueOf(REAL_CUSTOMER.getId()));
        when(request.getParameter("name")).thenReturn(REAL_CUSTOMER.getName());
        when(request.getParameter("surname")).thenReturn(REAL_CUSTOMER.getSurname());
        when(request.getParameter("phone")).thenReturn(REAL_CUSTOMER.getPhoneNumber());
        when(request.getParameter("birthday")).thenReturn(String.valueOf(REAL_CUSTOMER.getDateOfBirth()));
        when(request.getParameter("funds")).thenReturn(String.valueOf(REAL_CUSTOMER.getAvailableFunds()));
        when(request.getRequestDispatcher("/WEB-INF/jsp/successfulAdd.jsp")).thenReturn(requestDispatcher);
        ArgumentCaptor<String> captorName = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Customer> captorParameter = ArgumentCaptor.forClass(Customer.class);
        servlet.doPost(request, response);
        verify(customerDao, times(1)).insert(REAL_CUSTOMER);
        verify(request, times(1)).setAttribute(captorName.capture(), captorParameter.capture());
        verify(requestDispatcher, times(1)).forward(request, response);
        assertEquals("customer", captorName.getValue());
        assertEquals(REAL_CUSTOMER, captorParameter.getValue());
    }

    @Test
    void getWhenPathIsNotValid() throws Exception {
        when(request.getPathInfo()).thenReturn("/customer/not-valid-path");
        servlet.doGet(request, response);
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    void getWhenHandlerNotExistsInHandlerFactory() throws Exception {
        when(request.getPathInfo()).thenReturn("/Not-Valid-Path");
        servlet.doGet(request, response);
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    void postWhenHandlerNotExistsInHandlerFactory() throws Exception {
        when(request.getPathInfo()).thenReturn("/Not-Valid-Path");
        servlet.doPost(request, response);
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}