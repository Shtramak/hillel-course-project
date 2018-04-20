package com.courses.tellus.entity.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class University  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uniId;

    @Column(name = "name_of_university")
    private String nameOfUniversity;

    private String address;

    private String specialization;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    @ManyToMany
    @JoinTable(
            name = "univerity_student",
            joinColumns = @JoinColumn(name = "uni_id"),
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

    @Override
    public String toString() {
        return "university{"
                + "uniId=" + uniId
                + ", nameOfUniversity='" + nameOfUniversity + '\''
                + ", address='" + address + '\''
                + ", specialization='" + specialization + '\''
                + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof University)) {
            return false;
        }
        final University that = (University) obj;
        return getUniId() == that.getUniId()
                && Objects.equals(getNameOfUniversity(), that.getNameOfUniversity())
                && Objects.equals(getAddress(), that.getAddress())
                && Objects.equals(getSpecialization(), that.getSpecialization());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUniId(), getNameOfUniversity(), getAddress(), getSpecialization());
    }
}
