package com.courses.tellus.entity;

import java.util.Date;

public class Subject {

    private int subjectId;
    private String name;
    private String description;
    private boolean valid;
    private Date dateOfCreation;

    public Subject() {
        super();
    }

    public Subject(final String name, final String description, final boolean valid, final Date dateOfCreation) {
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.dateOfCreation = dateOfCreation;
    }

    public Subject(final int subjectId, final String name, final String description,
                   final boolean valid, final Date dateOfCreation) {
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.dateOfCreation = dateOfCreation;
    }

    public int getId() {
        return subjectId;
    }

    public void setId(final int subjectId) {
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
                + "id=" + subjectId
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", valid=" + valid
                + ", dateOfCreation=" + dateOfCreation
                + '}';
    }
}
