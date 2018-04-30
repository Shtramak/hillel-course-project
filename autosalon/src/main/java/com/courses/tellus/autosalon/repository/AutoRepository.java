package com.courses.tellus.autosalon.repository;

import com.courses.tellus.autosalon.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
@NoRepositoryBean
public interface AutoRepository extends JpaRepository<Auto, Long> {
}
