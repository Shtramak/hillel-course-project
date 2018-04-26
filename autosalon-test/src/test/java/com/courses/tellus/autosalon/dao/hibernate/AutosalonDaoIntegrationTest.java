package com.courses.tellus.autosalon.dao.hibernate;

import com.courses.tellus.autosalon.config.hibernate.EntityFactory;
import com.courses.tellus.autosalon.model.Autosalon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutosalonDaoIntegrationTest {
    private AutosalonDao autosalonDao;
    private final Autosalon autosalon = new Autosalon(1L, "Geely", "China", "00000");
    private Autosalon autoSALON = new Autosalon(1L, "Bavaria", "Kyiv", "00001");

    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        entityManager = EntityFactory.getFactory().createEntityManager();
        autosalonDao = new AutosalonDao(entityManager);
    }

    @Test
    public void testGetAllWhenResultTrue(){
        assertThat(autosalonDao.getAll().size(), is(1));
    }

    @Test
    public void testGetAllWhenResultFalse(){
        assertNotEquals(1, autosalonDao.getAll().isEmpty());
    }

    @Test
    public void testInsertWhenResultTrue(){
        assertThat(autosalonDao.insert(new Autosalon()), is(1));
    }

    @Test
    public void testInsertWhenResultFalse(){
        assertThat(autosalonDao.insert(null), is(-1));
    }

    @Test
    public void testUpdateWhenResultTrue(){
        autoSALON.setName("Toyota");
        autoSALON.setAddress("Camry");
        assertThat(autosalonDao.update(autoSALON), is(1));
    }

    @Test
    public void testUpdateWhenResultFalse(){
        assertThat(autosalonDao.update(null), is(-1));
    }

    @Test
    public void testDeleteWhenResultTrue(){
        assertThat(autosalonDao.delete(1L), is(1));
    }

    @Test
    public void testDeleteWhenResultFalse(){
        assertThat(autosalonDao.delete(8L), is(-1));
    }

    @Test
    public void testGetByIdWhenresultTrue(){
        assertTrue(autosalonDao.getById(2L).isPresent());
    }

    @Test
    public void testGetByIdWhenresultFalse(){
        assertThat(autosalonDao.getById(null), is(Optional.empty()));
    }

}
