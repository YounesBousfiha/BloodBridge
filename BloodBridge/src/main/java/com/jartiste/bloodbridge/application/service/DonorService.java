package com.jartiste.bloodbridge.application.service;

import com.jartiste.bloodbridge.domain.repository.DonorRepository;

public class DonorService {

    private final DonorRepository donorRepository;


    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }
}
