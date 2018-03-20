package com.courses.tellus.autosalon.model;

public class Autosalon {
    private Long id;
    private String name;
    private String address;
    private String telephone;

    public Autosalon() {
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
    public String toString() {
        return "Autosalon{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", address='" + address + '\''
                +  ", telephone=" + telephone + '}';
    }
}
