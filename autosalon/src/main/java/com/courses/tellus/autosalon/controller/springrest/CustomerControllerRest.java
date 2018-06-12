package com.courses.tellus.autosalon.controller.springrest;

import com.courses.tellus.autosalon.model.Customer;
import com.courses.tellus.autosalon.model.dto.CustomerDto;
import com.courses.tellus.autosalon.service.springrest.CustomerServiceRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springrest/autosalon/customer")
public class CustomerControllerRest {
    private final transient CustomerServiceRest customerService;

    @Autowired
    public CustomerControllerRest(final CustomerServiceRest customerService) {
        this.customerService = customerService;
    }

    /**
     * @return JSON with list of Customers.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Customer> getAll() {
        return customerService.getAll();
    }

    /**
     * @param customerId customer id from Http request.
     * @return JSON with Customer with specified id.
     * @throws IllegalArgumentException in case if there's no Customer with specified id.
     */
    @GetMapping(value = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Customer getById(@PathVariable("id") final Long customerId) {
        return customerService.getById(customerId).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * @param customerDto data transfer object of Customer entity.
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody final CustomerDto customerDto) {
        customerService.insert(customerDto);
    }

    /**
     * @param customerDto data transfer object of Customer entity.
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@RequestBody final CustomerDto customerDto) {
        if (customerService.getById(customerDto.getId()).isPresent()) {
            customerService.update(customerDto);
        } else {
            throw new IllegalArgumentException("Update failed! No user with id = " + customerDto.getId());
        }
    }

    /**
     * @param customerId customer id from Http request.
     */
    @DeleteMapping("/{id:[0-9]+}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long customerId) {
        if (customerService.getById(customerId).isPresent()) {
            customerService.delete(customerId);
        } else {
            throw new IllegalArgumentException("Delete failed! No user with id = " + customerId);
        }

    }

    /**
     * @param exception IllegalArgumentException exception thrown from CustomerControllerRest methods.
     * @return ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST).
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalExceptionHandler(final IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}