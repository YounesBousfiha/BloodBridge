package com.jartiste.bloodbridge.domain.entity;

import com.jartiste.bloodbridge.domain.enums.StatusReceiver;
import com.jartiste.bloodbridge.domain.enums.UrgentReceveur;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "receiver")
public class Receiver extends Personne {

    @NotNull
    @Enumerated(EnumType.STRING)
    private UrgentReceveur urgentReceveur;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusReceiver statusReceiver;

    @Column(name = "received_bags")
    private int receivedBags;

    public Receiver() {
        /* Default constructor */
    }

    public UrgentReceveur getUrgentReceveur() {
        return urgentReceveur;
    }

    public void setUrgentReceveur(UrgentReceveur urgentReceveur) {
        this.urgentReceveur = urgentReceveur;
    }

    public StatusReceiver getStatusReceiver() {
        return statusReceiver;
    }

    public void setStatusReceiver(StatusReceiver statusReceiver) {
        this.statusReceiver = statusReceiver;
    }

    public int getReceivedBags() {
        return receivedBags;
    }

    public void setReceivedBags(int receivedBags) {
        this.receivedBags = receivedBags;
    }

    public int getRequiredBags() {
        return urgentReceveur.getRequiredBags();
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "id=" + getId() +
                ", firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", cin=" + getCin() +
                ", bloodType=" + getBloodType() +
                ", telephone=" + getTelephone() +
                ", dateDeNaissance=" + getDateDeNaissance() +
                ", urgentReceveur=" + urgentReceveur +
                ", statusReceiver=" + statusReceiver +
                ", receivedBags=" + receivedBags +
                "} " + super.toString();
    }
}
