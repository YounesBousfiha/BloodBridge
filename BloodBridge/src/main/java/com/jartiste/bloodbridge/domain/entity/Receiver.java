package com.jartiste.bloodbridge.domain.entity;

import com.jartiste.bloodbridge.domain.enums.StatusReceveur;
import com.jartiste.bloodbridge.domain.enums.UrgentReceveur;

import java.util.Set;

public class Receiver extends Personne {
    private UrgentReceveur urgentReceveur;
    private StatusReceveur statusReceveur;
    private Set<Donor> donors;



    public Receiver() {
        /* Default constructor */
    }

    public UrgentReceveur getUrgentReceveur() {
        return urgentReceveur;
    }

    public void setUrgentReceveur(UrgentReceveur urgentReceveur) {
        this.urgentReceveur = urgentReceveur;
    }

    public StatusReceveur getStatusReceveur() {
        return statusReceveur;
    }

    public void setStatusReceveur(StatusReceveur statusReceveur) {
        this.statusReceveur = statusReceveur;
    }

    public Set<Donor> getDonors() {
        return donors;
    }

    public void setDonors(Set<Donor> donors) {
        this.donors = donors;
    }
}
