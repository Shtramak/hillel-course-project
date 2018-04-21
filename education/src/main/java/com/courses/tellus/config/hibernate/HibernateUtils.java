package com.courses.tellus.config.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class HibernateUtils {

    private static final String PERSIST_UNIT = "education-postgres-local";
    private static EntityManagerFactory managerFactory;

    private HibernateUtils() {
    }

    /**
     * Create and return singleton of entity if it not exists or return exists EntityManagerFactory.
     *
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getManagerFactory() {
        if (managerFactory == null) {
            managerFactory = Persistence.createEntityManagerFactory(PERSIST_UNIT);
        }
        return managerFactory;
    }

    /**
     * Method for returning entity manager in pool.
     */
    public static void close() {
        managerFactory.close();
    }
}
