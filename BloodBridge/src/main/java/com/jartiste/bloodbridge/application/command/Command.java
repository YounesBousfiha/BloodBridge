package com.jartiste.bloodbridge.application.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Command {
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    default <T> T getService(HttpServletRequest req, Class<T> serviceClass) {
        Object service = req.getServletContext().getAttribute(serviceClass.getSimpleName());
        if(serviceClass.isInstance(service)) {
            return serviceClass.cast(service);
        }
        throw new IllegalStateException("Service not found or wrong tye : " + serviceClass.getSimpleName());
    }
}
