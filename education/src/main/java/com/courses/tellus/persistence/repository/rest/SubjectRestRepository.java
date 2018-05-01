package com.courses.tellus.persistence.repository.rest;

import com.courses.tellus.entity.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRestRepository extends JpaRepository<Subject, Long> {
}
