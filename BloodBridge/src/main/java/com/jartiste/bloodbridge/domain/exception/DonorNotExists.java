package com.jartiste.bloodbridge.domain.exception;

public class DonorNotExists extends RuntimeException {
    public DonorNotExists(String message) {
        super(message);
    }
}
