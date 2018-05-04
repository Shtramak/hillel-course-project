package com.courses.tellus.autosalon.repository;

import com.courses.tellus.autosalon.model.Autosalon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutosalonRepository extends JpaRepository<Autosalon, Long> {
}
