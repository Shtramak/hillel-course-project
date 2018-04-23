package com.courses.tellus.autosalon.dao.hibernate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.courses.tellus.autosalon.exception.DaoException;
import com.courses.tellus.autosalon.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerDaoUnitTest {
    @Mock
    private EntityManager entityManager;
    @Mock
    private Customer customer;
    @Mock
    private EntityTransaction transaction;
    private CustomerDao customerDao;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        customerDao = new CustomerDao(entityManager);
    }

    @Test
    void getAllWhenEntriesExistReturnsListOfCustomers(){
        List<Customer> customers = Collections.singletonList(customer);
        TypedQuery query = mock(TypedQuery.class);
        given(entityManager.createQuery(anyString(), anyObject())).willReturn(query);
        given(query.getResultList()).willReturn(customers);
        assertEquals(customers, customerDao.getAll());
    }

    @Test
    void getAllWhenNoEntriesReturnsEmptyList(){
        TypedQuery query = mock(TypedQuery.class);
        given(entityManager.createQuery(anyString(), anyObject())).willReturn(query);
        given(query.getResultList()).willReturn(Collections.EMPTY_LIST);
        assertEquals(Collections.EMPTY_LIST, customerDao.getAll());
    }

    @Test
    void getByIdWhenCustomerExistsReturns1(){
        given(entityManager.find(anyObject(),anyLong())).willReturn(customer);
        assertEquals(customer,customerDao.getById(customer.getId()).get());
    }

    @Test
    void getByIdWhenCustomerNotExistsReturnsMinus1(){
        given(entityManager.find(anyObject(),anyLong())).willReturn(null);
        assertEquals(Optional.empty(),customerDao.getById(1L));
    }

    @Test
    void updateWhenCustomerExistsReturns1(){
        given(entityManager.getTransaction()).willReturn(transaction);
        given(entityManager.find(anyObject(),anyLong())).willReturn(customer);
        Integer actual = customerDao.update(customer);
        verify(transaction).begin();
        verify(transaction).commit();
        Integer expected = 1;
        assertEquals(expected,actual);
    }

    @Test
    void updateWhenCustomerNotExistsReturnsMinus1(){
        given(entityManager.getTransaction()).willReturn(transaction);
        Integer actual = customerDao.update(customer);
        verify(transaction).rollback();
        Integer expected = -1;
        assertEquals(expected,actual);
    }

    @Test
    void deleteWhenCustomerExistsReturns1(){
        given(entityManager.getTransaction()).willReturn(transaction);
        given(entityManager.find(anyObject(),anyLong())).willReturn(customer);
        Integer actual = customerDao.delete(customer.getId());
        verify(transaction).begin();
        verify(transaction).commit();
        Integer expected = 1;
        assertEquals(expected,actual);
    }

    @Test
    void deleteWhenCustomerNotExistsReturnsMinus1(){
        given(entityManager.getTransaction()).willReturn(transaction);
        Integer actual = customerDao.delete(customer.getId());
        verify(transaction).rollback();
        Integer expected = -1;
        assertEquals(expected,actual);
    }


    @Test
    void insertCustomerReturns1(){
        given(entityManager.getTransaction()).willReturn(transaction);
        Integer actual = customerDao.insert(customer);
        verify(transaction).begin();
        verify(transaction).commit();
        Integer expected = 1;
        assertEquals(expected,actual);
    }

}
