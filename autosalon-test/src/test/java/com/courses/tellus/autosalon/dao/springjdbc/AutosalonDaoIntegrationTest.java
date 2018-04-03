package com.courses.tellus.autosalon.dao.springjdbc;

import com.courses.tellus.autosalon.dao.config.JdbcTemplatesConfigTest;
import com.courses.tellus.autosalon.model.Autosalon;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ContextConfiguration(classes = {JdbcTemplatesConfigTest.class, AutosalonDao.class})
@ExtendWith(SpringExtension.class)
public class AutosalonDaoIntegrationTest {

    @Autowired
    private AutosalonDao autosalonDao;

    private static final Autosalon autosalon = new Autosalon(1L, "Geely", "China", "00000");
    private static final Autosalon autosalonInTable = new Autosalon(2L, "Geely", "China", "00000");

    @Test
    public void testGetById() {
        Assertions.assertFalse(autosalonDao.getById(1L).toString().isEmpty());
    }
    @Test
    public void testGetByIdWhenWrongId() {
        Assertions.assertEquals(Optional.empty(), autosalonDao.getById(0L));
    }

    @Test
    public void testGetAll() {
        Assertions.assertFalse(autosalonDao.getAll().isEmpty());
    }

    @Test
    public void testInsert() {
        MatcherAssert.assertThat(autosalonDao.insert(autosalon), CoreMatchers.is(1));
    }

    @Test
    public void testUpdate() {
        MatcherAssert.assertThat(autosalonDao.update(autosalonInTable), CoreMatchers.is(1));
    }

    @Test
    public void testDelete() {
        MatcherAssert.assertThat(autosalonDao.delete(1L), CoreMatchers.is(1));
    }
}