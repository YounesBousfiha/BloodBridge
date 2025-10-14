package com.jartiste.bloodbridge.application.validation;

import com.jartiste.bloodbridge.domain.exception.InvalidDonorException;
import com.jartiste.bloodbridge.presentation.dto.DonorDTO;

public class DonorRequiredFieldsValidator {


    private DonorRequiredFieldsValidator() { /* change the constructure default behaviour */}

    public static void validate(DonorDTO donorDTO) {
        if(null == donorDTO) throw new InvalidDonorException("DonorDTO cannot be null");
        if(null == donorDTO.getFirstName() || donorDTO.getFirstName().isEmpty()) {
            throw new InvalidDonorException("FirstName is required");
        }

        if(null == donorDTO.getLastName() || donorDTO.getLastName().isEmpty()) {
            throw new InvalidDonorException("LastName is required");
        }

        if(null == donorDTO.getBloodType()) {
            throw new InvalidDonorException("BloodType is required");
        }

        if(null == donorDTO.getCin()) {
            throw new InvalidDonorException("CIN is required");
        }

        if(null == donorDTO.getDateDeNaissance()) {
            throw new InvalidDonorException("BirthDate is required");
        }

        if(null == donorDTO.getTelephone()) {
            throw new InvalidDonorException("Phone number is required");
        }
    }
}
