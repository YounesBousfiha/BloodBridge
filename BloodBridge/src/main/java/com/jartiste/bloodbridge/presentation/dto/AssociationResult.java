package com.jartiste.bloodbridge.presentation.dto;

public class AssociationResult {
    private final boolean success;
    private final String message;

    public AssociationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
