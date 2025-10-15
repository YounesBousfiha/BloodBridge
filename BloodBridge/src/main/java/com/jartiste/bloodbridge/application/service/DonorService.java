package com.jartiste.bloodbridge.application.service;

import com.jartiste.bloodbridge.application.mapper.DonorMapper;
import com.jartiste.bloodbridge.application.validation.DonorRequiredFieldsValidator;
import com.jartiste.bloodbridge.domain.entity.Donor;
import com.jartiste.bloodbridge.domain.entity.Receiver;
import com.jartiste.bloodbridge.domain.enums.StatusDonner;
import com.jartiste.bloodbridge.domain.exception.DonorNotExists;
import com.jartiste.bloodbridge.domain.repository.DonationRepository;
import com.jartiste.bloodbridge.domain.repository.DonorRepository;
import com.jartiste.bloodbridge.domain.repository.ReceiverRepository;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import com.jartiste.bloodbridge.presentation.dto.DonorDTO;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DonorService {

    private final Logger logger = AppLogger.getLogger(DonorService.class);
    private final DonorRepository donorRepository;
    private final ReceiverRepository receiverRepository;


    public DonorService(DonorRepository donorRepository, ReceiverRepository receiverRepository) {
        this.donorRepository = donorRepository;
        this.receiverRepository = receiverRepository;
    }

    public boolean createDonor(DonorDTO donorDTO) {
        try {
            DonorRequiredFieldsValidator.validate(donorDTO);

            Donor donor = DonorMapper.toEntity(donorDTO);

            donor.setCreatedAt(LocalDate.now());
            this.updateDonorStatus(donor);

            this.donorRepository.save(donor);

            return true;
        } catch (Exception e) {
            logger.error("Error while creating donor", e);
            return false;
        }
    }

    public DonorDTO updateDonor(DonorDTO donorDTO) {
        try {
            // DonorRequiredFieldsValidator.validate(donorDTO);

            Donor donor = DonorMapper.toEntity(donorDTO);

            this.updateDonorStatus(donor);

            Donor updatedDonor = this.donorRepository.save(donor);

            return DonorMapper.toDTO(updatedDonor);
        } catch (Exception e) {
            logger.error("Error while updating donor", e);
            return null;
        }
    }

    public boolean deleteDonor(String donorId) {

        Long id = Long.parseLong(donorId);
        try {
            boolean isExist = this.donorRepository.isExist(id);

            if(isExist) {
                this.donorRepository.deleteById(Long.parseLong(donorId));
                return true;
            } else {
                throw new DonorNotExists("No Such Donor");
            }
        } catch (DonorNotExists e) {
            logger.error("Donor not Found: {}. Exception {} ", donorId, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error while removing donor", e);
            return false;
        }
    }

    public DonorDTO findDonorbyId(String donorId) {
        logger.error("DEBUG: Finding Donor by ID => {} ", donorId);
        Long id = Long.parseLong(donorId);
        logger.error("DEBUG: Finding Donor by ID (Long) => {} ", id);
        try {
            Optional<Donor> donorOpt = this.donorRepository.findById(id);

            if(donorOpt.isPresent()) {
                return DonorMapper.toDTO(donorOpt.get());
            } else {
                throw new DonorNotExists("No Such Donor");
            }
        } catch (Exception e) {
            logger.error("Error while Loading Donor by ID", e);
            return null;
        }
    }

    public List<DonorDTO> findAllDonors() {

        try {
            Optional<List<Donor>>  donors = this.donorRepository.findAll();

            if(donors.isPresent()) {
                return donors.get().stream()
                        .map(DonorMapper::toDTO)
                        .collect(Collectors.toList());
            } else {
                return List.of();
            }
        } catch (Exception e) {
            logger.error("Error while Loading Donors List", e);
            return List.of();
        }
    }

    private void updateDonorStatus(Donor donor) {
        int age = this.calculateTheAge(donor.getDateDeNaissance());

        logger.error("DEBUG: AGE = {}", donor);

        if (age >= 18 && age <= 65 && donor.getWeight() >= 50 &&
                (donor.getContraindications() == null || donor.getContraindications().isEmpty())) {
            donor.setStatusDonneur(StatusDonner.AVAILABLE);
        } else {
            donor.setStatusDonneur(StatusDonner.INELIGIBLE);
        }
    }


    private int calculateTheAge(LocalDate birthday) {
        LocalDate today = LocalDate.now();

        int age = today.getYear() - birthday.getYear();
        if(today.getDayOfYear() < birthday.getDayOfYear()) {
            age--;
        }
        return age;
    }

}
