package com.courses.tellus.autosalon.config.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityFactory {

    private static final String PERSISTANCE_NAME = "autosalon";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getFactory() {
        if (factory == null){
            factory = Persistence.createEntityManagerFactory(PERSISTANCE_NAME);
        }
        return factory;
    }

    public static void close(){
        factory.close();
    }
}
