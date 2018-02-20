package com.courses.tellus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    private long id;
    private String houseNumber;
    private String street;
    private String apartmentNumber;
    private String city;
    private String state;
    private String country;
    private String zipcode;

}
