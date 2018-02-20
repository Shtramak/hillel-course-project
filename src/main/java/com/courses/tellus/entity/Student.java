package com.courses.tellus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String uniqueRegistrationNumber;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}
