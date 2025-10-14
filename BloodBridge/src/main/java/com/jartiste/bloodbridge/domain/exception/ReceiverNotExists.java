package com.jartiste.bloodbridge.domain.exception;

public class ReceiverNotExists extends RuntimeException {
    public ReceiverNotExists(String message) {
        super(message);
    }
}
