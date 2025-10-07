package com.jartiste.bloodbridge.domain.entity;


import com.jartiste.bloodbridge.domain.enums.Contraindication;
import com.jartiste.bloodbridge.domain.enums.StatusDonneur;

import java.time.LocalDate;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;


@Entity
public class Donor extends Personne {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Set<Contraindication> contraindications;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusDonneur statusDonneur;

    @NotNull
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

    public StatusDonneur getStatusDonneur() {
        return statusDonneur;
    }

    public void setStatusDonneur(StatusDonneur statusDonneur) {
        this.statusDonneur = statusDonneur;
    }

    public LocalDate getDernierDon() {
        return dernierDon;
    }

    public void setDernierDon(LocalDate dernierDon) {
        this.dernierDon = dernierDon;
    }
}
