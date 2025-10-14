package com.jartiste.bloodbridge.presentation.dto;

import com.jartiste.bloodbridge.domain.enums.BloodType;
import com.jartiste.bloodbridge.domain.enums.StatusReceiver;
import com.jartiste.bloodbridge.domain.enums.UrgentReceveur;

public class ReceiverDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String cin;
    private BloodType bloodType;
    private String phoneNumber;
    private UrgentReceveur urgencyLevel;
    private StatusReceiver statusReceiver;
    private int requiredBags;
    private int receivedBags;

    public ReceiverDTO() {
        // Default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UrgentReceveur getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(UrgentReceveur urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public StatusReceiver getStatusReceiver() {
        return statusReceiver;
    }

    public void setStatusReceiver(StatusReceiver statusReceiver) {
        this.statusReceiver = statusReceiver;
    }

    public int getRequiredBags() {
        return requiredBags;
    }

    public void setRequiredBags(int requiredBags) {
        this.requiredBags = requiredBags;
    }

    public int getReceivedBags() {
        return receivedBags;
    }

    public void setReceivedBags(int receivedBags) {
        this.receivedBags = receivedBags;
    }

    @Override
    public String toString() {
        return "ReceiverDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cin='" + cin + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", urgencyLevel='" + urgencyLevel + '\'' +
                ", statusReceiver='" + statusReceiver + '\'' +
                ", requiredBags=" + requiredBags +
                ", receivedBags=" + receivedBags +
                '}';
    }
}
