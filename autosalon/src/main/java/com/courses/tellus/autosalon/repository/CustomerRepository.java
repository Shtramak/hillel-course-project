package com.courses.tellus.autosalon.repository;

import com.courses.tellus.autosalon.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
