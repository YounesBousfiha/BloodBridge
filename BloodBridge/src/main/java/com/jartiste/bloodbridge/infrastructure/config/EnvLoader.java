package com.jartiste.bloodbridge.infrastructure.config;

import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;

import java.util.Objects;

public class EnvLoader {
    private static boolean loaded = false;
    private static final Logger logger = AppLogger.getLogger(EnvLoader.class);
    public static void load() {
        if (!loaded) {
            Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

            System.setProperty("DB_URL", Objects.requireNonNull(dotenv.get("DB_URL")));
            System.setProperty("DB_USER", Objects.requireNonNull(dotenv.get("DB_USER")));
            System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));

            loaded = true;
            logger.error("Variables d'environnement chargées depuis .env");
        }
    }
}