package com.courses.tellus.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_id")
    private Long subjectId;
    private String name;
    private String description;
    private boolean valid;
    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;

    /**
     * Empty Object constructor.
     */
    public Subject() {
    }

    /**
     * Constructor for creating new Object.
     *
     * @param name title of the subject
     * @param description small description
     * @param valid deprecation mark
     * @param date of creation this object
     */
    public Subject(final String name, final String description, final boolean valid,
                   final String date) {
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.dateOfCreation = LocalDate.parse(date);
    }

    /**
     * Constructor for creating new Object obtained from database.
     *
     * @param subjectId object database id
     * @param name title of the subject
     * @param description small description
     * @param valid deprecation mark
     * @param date of creation this object
     */
    public Subject(final Long subjectId, final String name, final String description,
                   final boolean valid, final String date) {
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.dateOfCreation = LocalDate.parse(date);
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(final Long subjectId) {
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

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(final LocalDate date) {
        this.dateOfCreation = date;
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
        return isValid() == subject.isValid()
                && Objects.equals(getSubjectId(), subject.getSubjectId())
                && Objects.equals(getName(), subject.getName())
                && Objects.equals(getDescription(), subject.getDescription())
                && Objects.equals(getDateOfCreation(), subject.getDateOfCreation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubjectId(), getName(), getDescription(), isValid(), getDateOfCreation());
    }
}
