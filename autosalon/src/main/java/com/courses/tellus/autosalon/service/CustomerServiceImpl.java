package com.courses.tellus.autosalon.service;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.dao.springjdbc.CustomerDao;
import com.courses.tellus.autosalon.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final transient CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(final CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public Optional<Customer> getById(final Long customerId) {
        return customerDao.getById(customerId);
    }

    @Override
    public Integer insert(final Customer customer) {
        return customerDao.insert(customer);
    }

    @Override
    public Integer delete(final Long customerId) {
        return customerDao.delete(customerId);
    }
}