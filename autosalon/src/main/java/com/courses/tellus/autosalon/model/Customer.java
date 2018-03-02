package com.courses.tellus.autosalon.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {
    private long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private double availableFunds;

    public Customer() {
    }

    public Customer(long id, String name, String surname, LocalDate dateOfBirth, String phoneNumber, double availableFunds) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.availableFunds = availableFunds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(double availableFunds) {
        this.availableFunds = availableFunds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        if (id != customer.id) {
            return false;
        }
        if (!name.equals(customer.name)) {
            return false;
        }
        if (!surname.equals(customer.surname)) {
            return false;
        }
        return dateOfBirth.equals(customer.dateOfBirth);
    }

    @Override
    public int hashCode() {
        final int bits = 32;
        int result = (int) (id ^ (id >>> bits));
        final int primeNumber = 31;
        result = primeNumber * result + name.hashCode();
        result = primeNumber * result + surname.hashCode();
        result = primeNumber * result + dateOfBirth.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", dateOfBirth=" + dateOfBirth
                + ", availableFunds=" + availableFunds
                + '}';
    }
}
