package com.jartiste.bloodbridge.domain.enums;

public enum BloodType {
    AB_PLUS,
    AB_MOIN,
    A_PLUS,
    A_MOIN,
    B_PLUS,
    B_MOIN,
    O_PLUS,
    O_MOIN;


    public boolean isCompatibleWith(BloodType bloodType) {
        return switch (this) {
            case AB_PLUS -> true;
            case AB_MOIN -> bloodType == AB_MOIN || bloodType == A_MOIN || bloodType == B_MOIN || bloodType == O_MOIN;
            case A_PLUS -> bloodType == A_PLUS || bloodType == A_MOIN || bloodType == O_PLUS || bloodType == O_MOIN;
            case A_MOIN -> bloodType == A_MOIN || bloodType == O_MOIN;
            case B_PLUS -> bloodType == B_PLUS || bloodType == B_MOIN || bloodType == O_PLUS || bloodType == O_MOIN;
            case B_MOIN -> bloodType == B_MOIN || bloodType == O_MOIN;
            case O_PLUS -> bloodType == O_PLUS || bloodType == O_MOIN;
            case O_MOIN -> bloodType == O_MOIN;
        };
    }
}
