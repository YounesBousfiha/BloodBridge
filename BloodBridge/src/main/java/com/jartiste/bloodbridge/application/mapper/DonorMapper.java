package com.jartiste.bloodbridge.application.mapper;

import com.jartiste.bloodbridge.domain.entity.Donor;
import com.jartiste.bloodbridge.presentation.dto.DonorDTO;

public class DonorMapper {


    private DonorMapper() {/* change the constructure default behaviour */}

    public static DonorDTO toDTO(Donor donor) {
        if(null == donor) { return null; }

        DonorDTO dto = new DonorDTO();
        dto.setId(donor.getId());
        dto.setFirstName(donor.getFirstName());
        dto.setLastName(donor.getLastName());
        dto.setCin(donor.getCin());
        dto.setBloodType(donor.getBloodType());
        dto.setTelephone(donor.getTelephone());
        dto.setDateDeNaissance(donor.getDateDeNaissance());
        dto.setContraindications(donor.getContraindications());
        dto.setStatusDonneur(donor.getStatusDonneur());
        dto.setWeight(donor.getWeight());
        dto.setDernierDon(donor.getDernierDon());
        dto.setCreatedAt(donor.getCreatedAt());

        return dto;
    }

    public static Donor toEntity(DonorDTO dto) {
        if(null == dto ) return  null;
        Donor donor = new Donor();
        donor.setId(dto.getId());
        donor.setFirstName(dto.getFirstName());
        donor.setLastName(dto.getLastName());
        donor.setCin(dto.getCin());
        donor.setBloodType(dto.getBloodType());
        donor.setTelephone(dto.getTelephone());
        donor.setDateDeNaissance(dto.getDateDeNaissance());
        donor.setContraindications(dto.getContraindications());
        donor.setWeight(dto.getWeight());
        donor.setStatusDonneur(dto.getStatusDonneur());
        donor.setDernierDon(dto.getDernierDon());
        donor.setCreatedAt(dto.getCreatedAt());

        return donor;
    }
}
