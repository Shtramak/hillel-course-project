package com.courses.tellus.autosalon.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "available_funds")
    private double availableFunds;
    @ManyToMany(mappedBy = "customers", fetch = FetchType.EAGER)
    private Set<Autosalon> autosalons;

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

    public Set<Autosalon> getAutosalons() {
        return autosalons;
    }

    public void setAutosalons(Set<Autosalon> autosalons) {
        this.autosalons = autosalons;
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
        return id == customer.id
                && Objects.equals(name, customer.name)
                && Objects.equals(surname, customer.surname)
                && Objects.equals(dateOfBirth, customer.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Customer{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", dateOfBirth=" + dateOfBirth
                + ", phoneNumber='" + phoneNumber + '\''
                + ", availableFunds=" + availableFunds
                + ", autosalons=" + autosalons
                + '}';
    }
}