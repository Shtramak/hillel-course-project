package com.courses.tellus.autosalon.dao.hibernate;

import com.courses.tellus.autosalon.dao.AutosalonDaoInterface;
import com.courses.tellus.autosalon.model.Auto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

@Repository
public class AutoDao implements AutosalonDaoInterface<Auto> {

    private EntityManagerFactory  managerFactory = Persistence.createEntityManagerFactory("autosalon");
    private EntityManager entityManager;

    public AutoDao() {
        entityManager = managerFactory.createEntityManager();
    }

    @Override
    public List<Auto> getAll() {
        return null;
    }

    @Override
    public Optional<Auto> getById(Long idAuto) {
        entityManager.getTransaction().begin();
        Auto auto = entityManager.find(Auto.class, idAuto);
        entityManager.getTransaction().commit();
        return Optional.of(auto);
    }

    @Override
    public Integer update(Auto auto) {
        entityManager.getTransaction().begin();
        entityManager.merge(auto);
        entityManager.getTransaction().commit();
        return 1;
    }

    @Override
    public Integer delete(Long idAuto) {
        entityManager.getTransaction().begin();
        entityManager.remove(idAuto);
        entityManager.getTransaction().commit();
        return 1;
    }

    @Override
    public Integer insert(Auto auto) {
        entityManager.getTransaction().begin();
        entityManager.persist(auto);
        entityManager.getTransaction().commit();
        return 1;
    }
}
