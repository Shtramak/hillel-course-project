package com.courses.tellus.autosalon.controller.springrest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.model.Customer;
import com.courses.tellus.autosalon.model.dto.CustomerDto;
import com.courses.tellus.autosalon.service.springrest.CustomerServiceImplRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CustomerControllerRestUnitTest {

    @Mock
    private Customer customer;
    @Mock
    private CustomerDto customerDto;
    @Mock
    private CustomerServiceImplRest customerService;
    @Mock
    private CustomerControllerRest customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerController = new CustomerControllerRest(customerService);
    }

    @Test
    void getAllWhenTableIsNotEmptyReturnsListOfCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerService.getAll()).thenReturn(customers);
        assertEquals(customers, customerController.getAll());
    }

    @Test
    void getByIdWhenIdExistsReturnsCustomer() {
        when(customerService.getById(anyLong())).thenReturn(Optional.of(customer));
        assertEquals(customer, customerController.getById(1L));
    }

    @Test
    void getByIdWhenIdNotExistsThrowsIllegalArgumentException() {
        when(customerService.getById(1L)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> customerController.getById(1L));
    }

    @Test
    void deleteWhenIdExists() {
        when(customerService.getById(anyLong())).thenReturn(Optional.of(customer));
        customerController.delete(1L);
        verify(customerService).delete(1L);
    }

    @Test
    void deleteWhenIdNotExistsThrowsIllegalArgumentException() {
        when(customerService.getById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> customerController.delete(1L));
    }

    @Test
    void testInsertWhenResultTrue() {
        customerController.insert(customerDto);
        verify(customerService, times(1)).insert(customerDto);
    }

    @Test
    void updateWhenIdExists() {
        when(customerService.getById(anyLong())).thenReturn(Optional.of(customer));
        customerController.update(customerDto);
        verify(customerService, times(1)).update(customerDto);
    }

    @Test
    void updateWhenIdNotExistsThrowsIllegalArgumentException() {
        when(customerService.getById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> customerController.update(customerDto));
    }

    @Test
    void illegalExceptionHandlerTest() {
        IllegalArgumentException exception = new IllegalArgumentException("test message");
        ResponseEntity<String> actual = new CustomerControllerRest(customerService).illegalExceptionHandler(exception);
        ResponseEntity<String> expected = new ResponseEntity<>("test message", HttpStatus.BAD_REQUEST);
        assertEquals(expected, actual);
    }
}
