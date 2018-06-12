package com.courses.tellus.autosalon.service.springrest;

import java.util.Optional;

import com.courses.tellus.autosalon.model.Customer;
import com.courses.tellus.autosalon.model.dto.CustomerDto;

public interface CustomerServiceRest {
    /**
     * Method for retrieving all Customer from database.
     *
     * @return list of customers from database or empty list otherwise
     */
    Iterable<Customer> getAll();

    /**
     * Method for retrieving Customer from database by specified id.
     *
     * @param customerId id of the customer to be selected from database
     * @return an Optional with a present value if the specified value is non-null, otherwise an empty Optional
     */
    Optional<Customer> getById(Long customerId);

    /**
     * Method for creating new customer into database.
     *
     * @param customerDto customer for inserting
     * @return number of affected rows in database
     */
    Customer insert(CustomerDto customerDto);

    /**
     * Method for updating object in database.
     *
     * @param customerDto customer to be updated
     * @return number of affected rows in database
     */
    Customer update(CustomerDto customerDto);


    /**
     * Method for deleting customer from database by specified id.
     *
     * @param customerId id of the customer to be removed from database
     */
    void delete(Long customerId);

}
