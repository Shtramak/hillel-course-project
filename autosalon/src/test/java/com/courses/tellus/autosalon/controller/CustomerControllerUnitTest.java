package com.courses.tellus.autosalon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import com.courses.tellus.autosalon.dao.springjdbc.CustomerDao;
import com.courses.tellus.autosalon.model.Customer;
import com.courses.tellus.autosalon.service.CustomerService;
import com.courses.tellus.autosalon.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class CustomerControllerUnitTest {
    private static final Customer REAL_CUSTOMER =
            new Customer(1, "John", "Smith", LocalDate.of(2018, 2, 20), "(012)345-67-89", 10000.50);

    @Mock
    private Model model;
    @Mock
    private CustomerDao customerDao;

    private CustomerController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        CustomerService service = new CustomerServiceImpl(customerDao);
        controller = new CustomerController(service);
    }

    @Test
    void listOfCustomersReturnsJspPageName() {
        List<Customer> customers = Collections.emptyList();
        given(customerDao.getAll()).willReturn(customers);
        String actual = controller.listOfCustomers(model);
        verify(model).addAttribute("customers", customers);
        assertEquals("listCustomers", actual);
    }

    @Test
    void customerByIdWhenCustomerExists() {
        Optional<Customer> optCustomer = Optional.of(REAL_CUSTOMER);
        Long customerId = REAL_CUSTOMER.getId();
        given(customerDao.getById(customerId)).willReturn(optCustomer);
        String actual = controller.customerById(String.valueOf(customerId), model);
        verify(model).addAttribute("customer", REAL_CUSTOMER);
        assertEquals("customerById", actual);
    }

    @Test
    void customerByIdWhenCustomerNotExists() {
        Optional<Customer> optCustomer = Optional.empty();
        Long customerId = 2L;
        given(customerDao.getById(customerId)).willReturn(optCustomer);
        String actual = controller.customerById(String.valueOf(customerId), model);
        verify(model).addAttribute("customerId", String.valueOf(customerId));
        assertEquals("customerNotFound", actual);
    }

    @Test
    void deleteByIdWhenCustomerExists() {
        Optional<Customer> optCustomer = Optional.of(REAL_CUSTOMER);
        Long customerId = REAL_CUSTOMER.getId();
        given(customerDao.getById(customerId)).willReturn(optCustomer);
        given(customerDao.delete(customerId)).willReturn(1);
        String actual = controller.deleteById(String.valueOf(customerId), model);
        verify(model).addAttribute("customer", REAL_CUSTOMER);
        assertEquals("customerDeletedById", actual);
    }

    @Test
    void deleteByIdWhenCustomerNotExists() {
        Optional<Customer> optCustomer = Optional.empty();
        Long customerId = 2L;
        given(customerDao.getById(customerId)).willReturn(optCustomer);
        String actual = controller.deleteById(String.valueOf(customerId), model);
        verify(model).addAttribute("customerId", String.valueOf(customerId));
        assertEquals("customerNotFound", actual);
    }

    @Test
    void addCustomerPageReturnsJspPageName() {
        String actual = controller.addCustomerPage(model);
        assertEquals("addCustomer", actual);
    }

    @Test
    void addCustomerToBdReturnsJspPageName() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn(String.valueOf(REAL_CUSTOMER.getId()));
        when(request.getParameter("name")).thenReturn(REAL_CUSTOMER.getName());
        when(request.getParameter("surname")).thenReturn(REAL_CUSTOMER.getSurname());
        when(request.getParameter("phone")).thenReturn(REAL_CUSTOMER.getPhoneNumber());
        when(request.getParameter("birthday")).thenReturn(String.valueOf(REAL_CUSTOMER.getDateOfBirth()));
        when(request.getParameter("funds")).thenReturn(String.valueOf(REAL_CUSTOMER.getAvailableFunds()));
        given(customerDao.insert(REAL_CUSTOMER)).willReturn(1);
        String actual = controller.addCustomerToBd(request, model);
        verify(model).addAttribute("customer", REAL_CUSTOMER);
        assertEquals("successfulAdd", actual);
    }
}