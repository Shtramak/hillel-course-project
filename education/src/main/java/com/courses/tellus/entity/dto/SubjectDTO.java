package com.courses.tellus.entity.dto;

public class SubjectDTO {

    private String subjectId;
    private String name;
    private String description;
    private String valid;
    private String dateOfCreation;

    public SubjectDTO() {
    }

    public SubjectDTO(final String name, final String description, final String valid, final String dateOfCreation) {
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.dateOfCreation = dateOfCreation;
    }

    public SubjectDTO(final String subjectId, final String name, final String description, final String valid,
                      final String dateOfCreation) {
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.dateOfCreation = dateOfCreation;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(final String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(final String valid) {
        this.valid = valid;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(final String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
