package com.courses.tellus.entity.dto;

import java.util.Set;

import com.courses.tellus.entity.model.Student;
import com.courses.tellus.entity.model.Subject;

public class UniversityDto {

    private String uniId;
    private String nameOfUniversity;
    private String address;
    private String specialization;
    private Set<Subject> subjects;
    private Set<Student> students;

    public UniversityDto(){
    }

    public UniversityDto(final String uniId, final String nameOfUniversity,
                         final String address, final String specialization) {
        this.uniId = uniId;
        this.nameOfUniversity = nameOfUniversity;
        this.address = address;
        this.specialization = specialization;
    }

    public UniversityDto(final String nameOfUniversity, final String address,
                         final String specialization) {
        this.nameOfUniversity = nameOfUniversity;
        this.address = address;
        this.specialization = specialization;
    }

    public String getUniId() {
        return uniId;
    }

    public void setUniId(final String uniId) {
        this.uniId = uniId;
    }

    public String getNameOfUniversity() {
        return nameOfUniversity;
    }

    public void setNameOfUniversity(final String nameOfUniversity) {
        this.nameOfUniversity = nameOfUniversity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(final String specialization) {
        this.specialization = specialization;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(final Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(final Set<Student> students) {
        this.students = students;
    }
}