package com.jartiste.bloodbridge.domain.enums;

public enum UrgentReceveur {
    CRITICAL,
    URGENT,
    NORMAL;


    public int getRequiredBags() {
        return switch (this) {
            case CRITICAL -> 4;
            case URGENT -> 3;
            case NORMAL -> 1;
        };
    }

}
