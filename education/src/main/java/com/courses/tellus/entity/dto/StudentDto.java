package com.courses.tellus.entity.dto;

import java.util.Set;

import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.entity.model.University;

public class StudentDto {

    private String studentId;
    private String firstName;
    private String lastName;
    private String studentCardNumber;
    private String address;
    private Set<University> universities;
    private Set<Subject> subjects;

    public StudentDto() {
    }

    public StudentDto(final String studentId, final String firstName,
                      final String lastName, final String studentCardNumber, final String address) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentCardNumber = studentCardNumber;
        this.address = address;
    }

    public StudentDto(final String firstName, final String lastName, final String studentCardNumber, final String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentCardNumber = studentCardNumber;
        this.address = address;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(final String studentId) {
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

    public Set<University> getUniversities() {
        return universities;
    }

    public void setUniversities(final Set<University> universities) {
        this.universities = universities;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(final Set<Subject> subjects) {
        this.subjects = subjects;
    }
}