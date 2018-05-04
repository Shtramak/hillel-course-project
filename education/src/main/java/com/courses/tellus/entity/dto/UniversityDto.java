package com.courses.tellus.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UniversityDto {



    private String uniId;

    @NotBlank
    @Size(min = 3, max = 5)
    private String nameOfUniversity;

    @NotBlank
    @Size(min = 5, max = 45)
    private String address;

    @NotBlank
    @Size(min=5, max = 35)
    private String specialization;

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
}