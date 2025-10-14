package com.jartiste.bloodbridge.presentation.dto;

import java.time.LocalDate;

public class ReceiverDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String cin;
    private String bloodType;
    private String phoneNumber;
    private String urgencyLevel;
    private String statusReceiver;
    private String weight;
    private int requiredBags;
    private int receivedBags;
    private String dateOfBirth;

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

    public String getWeight() {
        return weight;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public String getStatusReceiver() {
        return statusReceiver;
    }

    public void setStatusReceiver(String statusReceiver) {
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
                ", weight='" + weight + '\'' +
                ", requiredBags=" + requiredBags +
                ", receivedBags=" + receivedBags +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
