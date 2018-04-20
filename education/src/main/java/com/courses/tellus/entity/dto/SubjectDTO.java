package com.courses.tellus.entity.dto;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "SubjectDTO{"
                + "subjectId='" + subjectId + '\''
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", valid='" + valid + '\''
                + ", dateOfCreation='" + dateOfCreation + '\'' + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SubjectDTO)) {
            return false;
        }
        final SubjectDTO that = (SubjectDTO) obj;
        return Objects.equals(getSubjectId(), that.getSubjectId())
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getDescription(), that.getDescription())
                && Objects.equals(getValid(), that.getValid())
                && Objects.equals(getDateOfCreation(), that.getDateOfCreation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubjectId(), getName(), getDescription(), getValid(), getDateOfCreation());
    }
}
