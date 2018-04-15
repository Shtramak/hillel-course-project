package com.courses.tellus.service;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.dto.SubjectDTO;
import com.courses.tellus.model.Subject;

public interface SubjectService {

    /**
     * Method for getting all subjects from database.
     *
     * @return list of subjects or empty list
     */
    List<Subject> getAll();

    /**
     * Method for getting subject from DB by id.
     *
     * @param subjectId id of the subject to select from DB
     * @return an Optional with a present value if the specified value
     *         is non-null, otherwise an empty Optional
     */
    Optional<Subject> getById(final Long subjectId);

    /**
     * Method for inserting new subject into database.
     *
     * @param subject  for inserting
     * @return number of affected rows int database
     */
    int insert(final SubjectDTO subject);

    /**
     * Method for deleting subject from database by id.
     *
     * @param subjectId id of the subject to remove from database
     * @return number of affected rows in database
     */
    int delete(final Long subjectId);

    /**
     * Method for updating new subject into database.
     *
     * @param subject for updating
     * @return number of affected rows int database
     */
    int update(final SubjectDTO subject);
}
