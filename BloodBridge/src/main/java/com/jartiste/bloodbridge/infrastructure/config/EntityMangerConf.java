package com.jartiste.bloodbridge.infrastructure.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityMangerConf {
    private static EntityManagerFactory emf;

    static {
        try {
            EnvLoader.load();
            String persistenceUnit = System.getProperty("test.active") != null ? "testPU" : "bloodbridgePU";
            emf = Persistence.createEntityManagerFactory(persistenceUnit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManagerFactory getEmf() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory not initialized");
        }
        return emf;
    }
    // Optional: Add a shutdown hook or method to close EMF on app shutdown
    /*public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }*/
}
