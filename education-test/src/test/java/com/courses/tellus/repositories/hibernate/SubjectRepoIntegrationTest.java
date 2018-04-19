package com.courses.tellus.repositories.hibernate;

import java.time.LocalDate;

import com.courses.tellus.config.hibernate.EntityFactory;
import com.courses.tellus.model.Subject;
import com.courses.tellus.repository.hibernate.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubjectRepoIntegrationTest {

    private SubjectRepository repository;

    @BeforeEach
    void setup() {
        repository = new SubjectRepository(EntityFactory.getManagerFactory().createEntityManager());
        repository.persist(new Subject("", "", true, LocalDate.of(2000, 05, 12)));
    }

    @Test
    void findAllTestAndReturnEntityList() {
        assertEquals();
    }
}
