package com.courses.tellus.entity;

import java.util.Date;
import java.util.Objects;

public class Subject {

    private Integer subjectId;
    private String name;
    private String description;
    private boolean valid;
    private Date dateOfCreation;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(final Integer subjectId) {
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

    public boolean isValid() {
        return valid;
    }

    public void setValid(final boolean valid) {
        this.valid = valid;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(final Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public String toString() {
        return "Subject{"
                + "subjectId=" + subjectId
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", valid=" + valid
                + ", dateOfCreation=" + dateOfCreation
                + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Subject)) {
            return false;
        }
        final Subject subject = (Subject) obj;
        return getSubjectId() == subject.getSubjectId()
                && isValid() == subject.isValid()
                && Objects.equals(getName(), subject.getName())
                && Objects.equals(getDescription(), subject.getDescription())
                && Objects.equals(getDateOfCreation(), subject.getDateOfCreation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubjectId(), getName(), getDescription(), isValid(), getDateOfCreation());
    }
}
