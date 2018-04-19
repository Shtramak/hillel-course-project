package com.courses.tellus.autosalon.config.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SuppressWarnings("PMD.ClassWithOnlyPrivateConstructorsShouldBeFinal")
public class EntityFactory {
    private static final String PERSISTANCE_NAME = "autosalon";
    private static EntityManagerFactory factory;

    private EntityFactory(){
    }

    /**
     * Create singleton for EntityManagerFactory.
     *
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTANCE_NAME);
        }
        return factory;
    }

    /**
     * Close EntityManagerFactory.
     */
    public static void close() {
        factory.close();
    }
}
