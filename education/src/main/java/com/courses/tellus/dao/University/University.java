package com.courses.tellus.dao.University;

import com.courses.tellus.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class University  {

    private int id;

    private String nameOfUniversity;

    private String address;

    private String specialization;

    public University() {
    }

    public University(int id, String nameOfUniversity) {
        this.id = id;
        this.nameOfUniversity = nameOfUniversity;
    }

    public University(int id, String nameOfUniversity, String address, String specialization) {
        this.id = id;
        this.nameOfUniversity = nameOfUniversity;
        this.address = address;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfUniversity() {
        return nameOfUniversity;
    }

    public void setNameOfUniversity(String nameOfUniversity) {
        this.nameOfUniversity = nameOfUniversity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", nameOfUniversity='" + nameOfUniversity + '\'' +
                ", address='" + address + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        University that = (University) o;

        if (id != that.id) return false;
        if (!nameOfUniversity.equals(that.nameOfUniversity)) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return specialization != null ? specialization.equals(that.specialization) : that.specialization == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nameOfUniversity.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        return result;
    }
}
