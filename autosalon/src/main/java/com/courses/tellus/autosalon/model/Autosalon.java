package com.courses.tellus.autosalon.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Autosalon")
public class Autosalon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autosalon_id")
    private Long id;
    private String name;
    private String address;
    private String telephone;
    @OneToMany(mappedBy = "autosalon", cascade = CascadeType.ALL)
    private Set<Auto> autos;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Autosalon_Customer",
            joinColumns = {@JoinColumn(name = "autosalon_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    private Set<Customer> customers;

    public Autosalon() {
    }

    public Autosalon(String name, String address, String telephone) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }

    public Autosalon(Long id, String name, String address, String telephone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Auto> getAutos() {
        return autos;
    }

    public void setAutos(Set<Auto> autos) {
        this.autos = autos;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Autosalon autosalon = (Autosalon) o;
        return Objects.equals(id, autosalon.id)
                && Objects.equals(name, autosalon.name)
                && Objects.equals(address, autosalon.address)
                && Objects.equals(telephone, autosalon.telephone)
                && Objects.equals(autos, autosalon.autos)
                && Objects.equals(customers, autosalon.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, telephone, autos, customers);
    }

    @Override
    public String toString() {
        return "Autosalon{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", address='" + address + '\''
                + ", telephone=" + telephone + '}';
    }
}