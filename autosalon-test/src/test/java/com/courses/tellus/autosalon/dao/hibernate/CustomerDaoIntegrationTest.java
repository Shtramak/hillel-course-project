package com.courses.tellus.autosalon.dao.hibernate;

import com.courses.tellus.autosalon.config.hibernate.EntityFactory;
import com.courses.tellus.autosalon.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDaoIntegrationTest {
    private static final Customer CUSTOMER =
            new Customer(1, "Ivan", "Ivanov", LocalDate.of(2018, 4, 20), "NoPhone", 10000);

    private static EntityManagerFactory factory;

    private EntityManager entityManager;
    private CustomerDao customerDao;

    @BeforeAll
    static void initEntityManagerFactory(){
        factory = EntityFactory.getFactory();
    }

    @BeforeEach
    void setup() {
        entityManager = factory.createEntityManager();
        customerDao = new CustomerDao(entityManager);
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("DELETE FROM Customer").executeUpdate();
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @AfterEach
    void tearDown(){
        entityManager.close();
    }

    @Test
    void getAllWhenEntriesExistReturnsListofCustomers() {
        nativeInsertOfCustomer(CUSTOMER);
        List<Customer> expected = Collections.singletonList(CUSTOMER);
        assertEquals(expected, customerDao.getAll());
    }

    @Test
    void getAllWhenNoEntriesReturnsEmptyList() {
        assertEquals(Collections.EMPTY_LIST, customerDao.getAll());
    }

    @Test
    void getByIdWhenEntriesExistReturnsOptionalWithCustomer() {
        nativeInsertOfCustomer(CUSTOMER);
        assertEquals(CUSTOMER, customerDao.getById(CUSTOMER.getId()).get());
    }

    @Test
    void getByIdWhenEntrieNotExistsReturnsOptionalEmpty() {
        assertEquals(Optional.empty(), customerDao.getById(CUSTOMER.getId()));
    }

    @Test
    void updateWhenEntrieExistReturns1() {
        nativeInsertOfCustomer(CUSTOMER);
        Customer customer = new Customer();
        customer.setId(CUSTOMER.getId());
        customer.setName("UpdatedName");
        assertEquals(Integer.valueOf(1), customerDao.update(customer));
        assertEquals(customer, customerDao.getById(CUSTOMER.getId()).get());
    }

    @Test
    void updateWhenEntrieNotExistReturnsMinus1() {
        assertEquals(Integer.valueOf(-1), customerDao.update(CUSTOMER));
    }

    @Test
    void deleteWhenEntrieExistReturns1() {
        nativeInsertOfCustomer(CUSTOMER);
        assertEquals(Integer.valueOf(1), customerDao.delete(CUSTOMER.getId()));
    }

    @Test
    void deleteWhenEntrieNotExistReturnsMinus1() {
        assertEquals(Integer.valueOf(-1), customerDao.delete(CUSTOMER.getId()));
    }

    @Test
    void insertCustomerReturns1() {
        assertEquals(Integer.valueOf(1), customerDao.insert(new Customer()));
    }

    private void nativeInsertOfCustomer(Customer customer) {
        String sql = "INSERT INTO " +
                "PUBLIC.CUSTOMER (CUSTOMER_ID, AVAILABLE_FUNDS, DATE_OF_BIRTH, NAME, PHONE_NUMBER, SURNAME)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(sql)
                .setParameter(1, customer.getId())
                .setParameter(2, customer.getAvailableFunds())
                .setParameter(3, customer.getDateOfBirth())
                .setParameter(4, customer.getName())
                .setParameter(5, customer.getPhoneNumber())
                .setParameter(6, customer.getSurname())
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}