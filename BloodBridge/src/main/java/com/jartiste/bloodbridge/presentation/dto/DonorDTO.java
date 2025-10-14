package com.jartiste.bloodbridge.presentation.dto;

import com.jartiste.bloodbridge.domain.enums.BloodType;
import com.jartiste.bloodbridge.domain.enums.Contraindication;
import com.jartiste.bloodbridge.domain.enums.StatusDonner;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;
import java.util.Set;

public class DonorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String cin;
    private BloodType bloodType;
    private String telephone;
    private Integer weight;
    private LocalDate dateDeNaissance;
    private LocalDate createdAt;
    private Set<Contraindication> contraindications;
    private StatusDonner statusDonneur;
    private Long receiverId; // Only expose receiver's id, not the whole object
    private LocalDate dernierDon;


    public DonorDTO() {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Contraindication> getContraindications() {
        return contraindications;
    }

    public void setContraindications(Set<Contraindication> contraindications) {
        this.contraindications = contraindications;
    }

    public StatusDonner getStatusDonneur() {
        return statusDonneur;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setStatusDonneur(StatusDonner statusDonneur) {
        this.statusDonneur = statusDonneur;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public LocalDate getDernierDon() {
        return dernierDon;
    }

    public void setDernierDon(LocalDate dernierDon) {
        this.dernierDon = dernierDon;
    }

    @Override
    public String toString() {
        return "DonorDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cin='" + cin + '\'' +
                ", bloodType=" + bloodType +
                ", telephone='" + telephone + '\'' +
                ", weight='" + weight + '\'' +
                ", dateDeNaissance=" + dateDeNaissance +
                ", createdAt=" + createdAt +
                ", contraindications=" + contraindications +
                ", statusDonneur=" + statusDonneur +
                ", receiverId=" + receiverId +
                ", dernierDon=" + dernierDon +
                '}';
    }
}
