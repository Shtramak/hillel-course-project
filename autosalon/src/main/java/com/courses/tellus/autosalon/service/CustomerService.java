package com.courses.tellus.autosalon.service;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.model.Customer;

public interface CustomerService {
    /**
     * Method for retrieving all Customer from database.
     *
     * @return list of customers from database or empty list otherwise
     */
    List<Customer> getAll();

    /**
     * Method for retrieving Customer from database by specified id.
     *
     * @param customerId id of the customer to be selected from database
     * @return an Optional with a present value if the specified value
     *         is non-null, otherwise an empty Optional
     */
    Optional<Customer> getById(Long customerId);

    /**
     * Method for creating new customer into database.
     *
     * @param customer customer for inserting
     * @return number of affected rows int database
     */
    Integer insert(Customer customer);

    /**
     * Method for deleting customer from database by specified id.
     *
     * @param customerId id of the customer to be removed from database
     * @return number of affected rows in database
     */
    Integer delete(Long customerId);
}
