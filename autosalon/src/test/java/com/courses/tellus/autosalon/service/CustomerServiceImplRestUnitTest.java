package com.courses.tellus.autosalon.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.model.Customer;
import com.courses.tellus.autosalon.model.dto.CustomerDto;
import com.courses.tellus.autosalon.repository.CustomerRepository;
import com.courses.tellus.autosalon.service.springrest.CustomerServiceImplRest;
import com.courses.tellus.autosalon.service.springrest.CustomerServiceRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CustomerServiceImplRestUnitTest {

    @Mock
    private CustomerRepository repository;
    @Mock
    private Customer customer;
    @Mock
    private CustomerDto customerDto;

    private CustomerServiceRest customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImplRest(repository);
    }

    @Test
    public void getAllWhenTableIsNotEmptyReturnsListOfCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(repository.findAll()).thenReturn(customers);
        assertThat(customerService.getAll(), is(customers));
    }

    @Test
    public void getByIdWhenIdExistsReturnsCustomer() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(customer));
        assertThat(customerService.getById(1L), is(Optional.of(customer)));
    }

    @Test
    public void getByIdWhenIdNotExistsReturnsOptionalEmpty() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThat(customerService.getById(1L), is(Optional.empty()));
    }

    @Test
    public void deleteTest() {
        customerService.delete(1L);
        verify(repository, atLeastOnce()).deleteById(anyLong());
    }

    @Test
    public void testInsertWhenResultTrue() {
        when(customerDto.getName()).thenReturn("Name");
        when(customerDto.getSurname()).thenReturn("Surname");
        when(customerDto.getDateOfBirth()).thenReturn("2018-05-01");
        when(customerDto.getPhoneNumber()).thenReturn("123456789");
        when(customerDto.getAvailableFunds()).thenReturn("20000");
        customerService.insert(customerDto);
        verify(repository, atLeastOnce()).save(anyObject());
    }

    @Test
    public void testUpdateWhenResultTrue() {
        when(customerDto.getId()).thenReturn(1L);
        when(customerDto.getName()).thenReturn("Name");
        when(customerDto.getSurname()).thenReturn("Surname");
        when(customerDto.getDateOfBirth()).thenReturn("2018-05-01");
        when(customerDto.getPhoneNumber()).thenReturn("123456789");
        when(customerDto.getAvailableFunds()).thenReturn("20000");
        customerService.update(customerDto);
        verify(repository, atLeastOnce()).save(anyObject());
    }

}
