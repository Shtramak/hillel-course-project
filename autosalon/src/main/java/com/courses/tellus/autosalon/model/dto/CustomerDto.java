package com.courses.tellus.autosalon.model.dto;

public class CustomerDto {
    private Long id;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String phoneNumber;
    private String availableFunds;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(final String availableFunds) {
        this.availableFunds = availableFunds;
    }
}
