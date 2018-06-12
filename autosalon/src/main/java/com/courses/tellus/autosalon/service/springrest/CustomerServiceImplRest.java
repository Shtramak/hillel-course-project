package com.courses.tellus.autosalon.service.springrest;

import java.time.LocalDate;
import java.util.Optional;

import com.courses.tellus.autosalon.model.Customer;
import com.courses.tellus.autosalon.model.dto.CustomerDto;
import com.courses.tellus.autosalon.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImplRest implements CustomerServiceRest {
    private final transient CustomerRepository repository;

    @Autowired
    public CustomerServiceImplRest(final CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Customer> getById(final Long customerId) {
        return repository.findById(customerId);
    }

    @Override
    public Customer insert(final CustomerDto customerDto) {
        final Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setDateOfBirth(LocalDate.parse(customerDto.getDateOfBirth()));
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAvailableFunds(Double.valueOf(customerDto.getAvailableFunds()));
        return repository.save(customer);
    }

    @Override
    public Customer update(final CustomerDto customerDto) {
        final Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setDateOfBirth(LocalDate.parse(customerDto.getDateOfBirth()));
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAvailableFunds(Double.valueOf(customerDto.getAvailableFunds()));
        return repository.save(customer);
    }

    @Override
    public void delete(final Long customerId) {
        repository.deleteById(customerId);
    }
}
