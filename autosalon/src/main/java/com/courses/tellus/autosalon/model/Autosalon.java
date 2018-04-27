package com.courses.tellus.autosalon.model;

import java.util.Objects;

public class Autosalon {

    private Long id;
    private String name;
    private String address;
    private String telephone;

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
                && Objects.equals(telephone, autosalon.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, telephone);
    }

    @Override
    public String toString() {
        return "Autosalon{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", address='" + address + '\''
                +  ", telephone=" + telephone + '}';
    }
}