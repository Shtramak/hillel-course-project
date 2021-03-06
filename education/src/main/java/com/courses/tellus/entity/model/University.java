package com.courses.tellus.entity.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "university")
public class University  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "univer_id")
    private Long uniId;

    @Column(name = "name_of_university")
    private String nameOfUniversity;

    private String address;

    private String specialization;

    @OneToMany(mappedBy = "university")
    @JsonIgnore
    private Set<Subject> subjects;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "university_student",
            joinColumns = @JoinColumn(name = "univer_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;

    public University() {
    }

    public University(final Long uniId, final String nameOfUniversity) {
        this.uniId = uniId;
        this.nameOfUniversity = nameOfUniversity;
    }

    public University(final Long uniId, final String nameOfUniversity, final String address,
                      final String specialization) {
        this.uniId = uniId;
        this.nameOfUniversity = nameOfUniversity;
        this.address = address;
        this.specialization = specialization;
    }

    public University(final String nameOfUniversity, final String address, final String specialization) {
        this.nameOfUniversity = nameOfUniversity;
        this.address = address;
        this.specialization = specialization;
    }

    public Long getUniId() {
        return uniId;
    }

    public void setUniId(final Long uniId) {
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

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "University{"
                + "uniId=" + uniId
                + ", nameOfUniversity='" + nameOfUniversity + '\''
                + ", address='" + address + '\''
                + ", specialization='" + specialization + '\''
                + ", subjects=" + subjects
                + ", students=" + students
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof University)) {
            return false;
        }
        University that = (University) obj;
        return Objects.equals(getUniId(), that.getUniId())
                && Objects.equals(getNameOfUniversity(), that.getNameOfUniversity())
                && Objects.equals(getAddress(), that.getAddress())
                && Objects.equals(getSpecialization(), that.getSpecialization())
                && Objects.equals(getSubjects(), that.getSubjects())
                && Objects.equals(getStudents(), that.getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUniId(), getNameOfUniversity(), getAddress(), getSpecialization(), getSubjects(),
                getStudents());
    }
}
