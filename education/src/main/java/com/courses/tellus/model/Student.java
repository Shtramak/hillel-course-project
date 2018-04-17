package com.courses.tellus.model;

import java.util.Objects;

public class Student {

    private long studentId;
    private String firstName;
    private String lastName;
    private String studentCardNumber;
    private String address;

    /**
     * Empty Object constructor.
     */
    public Student() {
    }

    /**
     * Constructor for creating new Object.
     *
     * @param firstName student name
     * @param lastName student surname
     * @param studentCardNumber persona id of student card
     * @param address living address
     */
    public Student(final String firstName, final String lastName, final String studentCardNumber,
                   final String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentCardNumber = studentCardNumber;
        this.address = address;
    }

    /**
     * Constructor for creating new Object obtained from database.
     *
     * @param studentId student id in database
     * @param firstName student name
     * @param lastName student surname
     * @param studentCardNumber persona id of student card
     * @param address living address
     */
    public Student(final long studentId, final String firstName, final String lastName, final String studentCardNumber,
                   final String address) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentCardNumber = studentCardNumber;
        this.address = address;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(final long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getStudentCardNumber() {
        return studentCardNumber;
    }

    public void setStudentCardNumber(final String studentCardNumber) {
        this.studentCardNumber = studentCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Student student = (Student) obj;
        return studentId == student.studentId
                && Objects.equals(firstName, student.firstName)
                && Objects.equals(lastName, student.lastName)
                && Objects.equals(studentCardNumber, student.studentCardNumber)
                && Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, studentCardNumber, address);
    }

    @Override
    public String toString() {
        return "Student{"
                + "studentId=" + studentId
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", studentCardNumber='" + studentCardNumber + '\''
                + ", address='" + address + '\''
                + '}';
    }
}