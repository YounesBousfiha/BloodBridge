package com.jartiste.bloodbridge.infrastructure.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityMangerConf {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bloodbridge");

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void close() {
        emf.close();
    }
}
