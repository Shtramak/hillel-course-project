package com.courses.tellus.entity;

import java.util.Objects;

public class Student {

    private long id;
    private String firstName;
    private String lastName;
    private String uniqueRegistrationNumber;
    private String address;

    public Student(long id, String firstName, String lastName, String uniqueRegistrationNumber, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.uniqueRegistrationNumber = uniqueRegistrationNumber;
        this.address = address;
    }

    public Student(long id, String size, String color, String gender) {
    }

    public long getId() {
        return 0;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUniqueRegistrationNumber() {
        return uniqueRegistrationNumber;
    }

    public void setUniqueRegistrationNumber(String uniqueRegistrationNumber) {
        this.uniqueRegistrationNumber = uniqueRegistrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(uniqueRegistrationNumber, student.uniqueRegistrationNumber) &&
                Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, uniqueRegistrationNumber, address);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", uniqueRegistrationNumber='" + uniqueRegistrationNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
