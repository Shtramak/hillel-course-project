package com.courses.tellus.model;

import java.util.Objects;

public class  University  {

    private Long uniId;
    private String nameOfUniversity;
    private String address;
    private String specialization;

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
        return "University{"
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
