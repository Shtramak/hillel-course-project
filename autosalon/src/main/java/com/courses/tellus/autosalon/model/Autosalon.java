package com.courses.tellus.autosalon.model;

public class Autosalon {
    private Long id;
    private String name;
    private String address;
    private String telophone;

    public Autosalon() {
    }

    public Autosalon(Long id, String name, String address, String telophone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telophone = telophone;
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

    public String getTelophone() {
        return telophone;
    }

    public void setTelophone(String telophone) {
        this.telophone = telophone;
    }

    @Override
    public String toString() {
        return "Autosalon{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", address='" + address + '\''
                +  ", telophone=" + telophone + '}';
    }
}
