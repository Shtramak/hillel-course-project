package com.courses.tellus.persistence.repository.rest;

import com.courses.tellus.entity.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRestRepository extends JpaRepository<Student, Long> {
}
