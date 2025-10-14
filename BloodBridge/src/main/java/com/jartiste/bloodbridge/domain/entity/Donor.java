package com.jartiste.bloodbridge.domain.entity;


import com.jartiste.bloodbridge.domain.enums.Contraindication;
import com.jartiste.bloodbridge.domain.enums.StatusDonner;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "donor")
public class Donor extends Personne {

    @NotNull
    @ElementCollection
    @CollectionTable(name = "donor_contraindications", joinColumns = @JoinColumn(name = "donor_id"))
    @Enumerated(EnumType.STRING)
    private Set<Contraindication> contraindications;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusDonner statusDonneur;

    @NotNull
    private Integer weight;

    private LocalDate dernierDon;

    public Donor() {
        /* Default constructor */
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

    public void setStatusDonneur(StatusDonner statusDonneur) {
        this.statusDonneur = statusDonneur;
    }

    public LocalDate getDernierDon() {
        return dernierDon;
    }

    public void setDernierDon(LocalDate dernierDon) {
        this.dernierDon = dernierDon;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", cin='" + getCin() + '\'' +
                ", bloodType=" + getBloodType() +
                ", telephone='" + getTelephone() + '\'' +
                ", dateDeNaissance=" + getDateDeNaissance() +
                ", createdAt=" + getCreatedAt() +
                "contraindications=" + contraindications +
                ", statusDonneur=" + statusDonneur +
                ", weight=" + weight +
                ", dernierDon=" + dernierDon +
                "} " + super.toString();
    }
}
