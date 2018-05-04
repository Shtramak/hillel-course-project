package com.courses.tellus.persistence.repository.rest;

import com.courses.tellus.entity.model.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends CrudRepository<University, Long> {
}
