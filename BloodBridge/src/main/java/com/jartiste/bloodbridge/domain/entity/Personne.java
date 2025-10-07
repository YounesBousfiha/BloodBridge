package com.jartiste.bloodbridge.domain.entity;

import com.jartiste.bloodbridge.domain.enums.BloodType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@MappedSuperclass
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 50)
    private String lastName;


    @NotNull
    @Pattern(regexp = "^[A-Za-z]{1,2}\\d{5,}$", message = "CIN must start with 1 or 2 letters and have at least 7 characters in total")
    private String cin;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @NotNull
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Invalid phone number")
    private String telephone;

    @NotNull
    @Temporal(TemporalType.DATE)

    private LocalDate dateDeNaissance;

    @NotNull
    @Column(updatable = false)
    private LocalDate createdAt;


    public Personne() {
        /* Default constructor */
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

}
