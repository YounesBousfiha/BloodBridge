package com.jartiste.bloodbridge.domain.exception;

public class DonorRepositoryException extends RuntimeException {
    public DonorRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
