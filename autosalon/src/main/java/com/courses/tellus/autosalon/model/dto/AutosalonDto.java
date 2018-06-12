package com.courses.tellus.autosalon.model.dto;

import java.util.Objects;

import com.courses.tellus.autosalon.model.Autosalon;

public class AutosalonDto {
    private Long id;
    private String name;
    private String address;
    private String telephone;

    public AutosalonDto() {
    }

    public AutosalonDto(Long id, String name, String address, String telephone) {
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
        if (!(o instanceof Autosalon)) {
            return false;
        }
        Autosalon autosalon = (Autosalon) o;
        return Objects.equals(getId(), autosalon.getId())
                && Objects.equals(getName(), autosalon.getName())
                && Objects.equals(getAddress(), autosalon.getAddress())
                && Objects.equals(getTelephone(), autosalon.getTelephone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getTelephone());
    }
}
