package com.courses.tellus.service;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.model.University;

public interface UniversityService {

        /**
         * Method for getting all universities from database.
         *
         * @return list of universities or empty list
         */

        List<University> getAll();

        /**
         * Method for getting university from DB by id.
         *
         * @param uniId id of the university to select from DB
         * @return an Optional with a present value if the specified value
         *         is non-null, otherwise an empty Optional
         */

        Optional<University> getById(final Long uniId);

        /**
         * Method for inserting new university into database.
         *
         * @param university  for inserting
         * @return number of affected rows int database
         */

        int insert(final University university);

        /**
         * Method for deleting university from database by id.
         *
         * @param uniId id of the university to remove from database
         * @return number of affected rows in database
         */

        int delete(final Long uniId);

        /**
         * Method for updatingting university from database by id.
         *
         * @param university to update
         * @return number of affected rows in database
         */

        int update(final University university);
}
