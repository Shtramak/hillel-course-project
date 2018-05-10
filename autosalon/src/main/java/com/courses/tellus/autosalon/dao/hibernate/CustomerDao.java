package com.courses.tellus.autosalon.dao.hibernate;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.model.Customer;

public class CustomerDao implements AutosalonDaoInterface<Customer> {
    private final transient EntityManager entityManager;

    public CustomerDao(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> getAll() {
        return entityManager
                .createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();
    }

    @Override
    public Optional<Customer> getById(final Long customerId) {
        final Customer customer = entityManager.find(Customer.class, customerId);
        if (customer != null) {
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    @Override
    public Integer update(final Customer customer) {
        entityManager.getTransaction().begin();
        final Customer persistedCustomer = entityManager.find(Customer.class, customer.getId());
        if (persistedCustomer != null) {
            persistedCustomer.setName(customer.getName());
            persistedCustomer.setSurname(customer.getSurname());
            persistedCustomer.setDateOfBirth(customer.getDateOfBirth());
            persistedCustomer.setPhoneNumber(customer.getPhoneNumber());
            persistedCustomer.setAvailableFunds(customer.getAvailableFunds());
            entityManager.merge(persistedCustomer);
            entityManager.getTransaction().commit();
            return 1;
        }
        entityManager.getTransaction().rollback();
        return -1;
    }

    @Override
    public Integer delete(final Long customerId) {
        entityManager.getTransaction().begin();
        final Customer customer = entityManager.find(Customer.class, customerId);
        if (customer != null) {
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
            return 1;
        }
        entityManager.getTransaction().rollback();
        return -1;
    }

    @Override
    public Integer insert(final Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        return 1;
    }
}