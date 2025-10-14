package com.jartiste.bloodbridge.infrastructure.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
