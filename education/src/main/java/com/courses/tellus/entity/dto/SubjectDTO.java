package com.courses.tellus.entity.dto;

import java.util.Set;

import com.courses.tellus.entity.model.Student;
import com.courses.tellus.entity.model.University;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubjectDTO {

    private String subjectId;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 5, max = 45)
    private String description;

    private String valid;

    @NotNull
    private String dateOfCreation;

    private Set<Student> students;
    private University university;

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(final Set<Student> students) {
        this.students = students;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(final University university) {
        this.university = university;
    }
}
