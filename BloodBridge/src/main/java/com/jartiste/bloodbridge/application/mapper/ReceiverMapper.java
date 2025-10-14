package com.jartiste.bloodbridge.application.mapper;

import com.jartiste.bloodbridge.domain.entity.Receiver;
import com.jartiste.bloodbridge.domain.enums.StatusReceiver;
import com.jartiste.bloodbridge.domain.enums.UrgentReceveur;
import com.jartiste.bloodbridge.presentation.dto.ReceiverDTO;

public class ReceiverMapper {

    private ReceiverMapper() {/* change the constructure default behaviour*/}

    public static ReceiverDTO toDTO(Receiver receiver) {
        if (null == receiver) return null;

        ReceiverDTO dto = new ReceiverDTO();
        dto.setId(receiver.getId());
        dto.setFirstName(receiver.getFirstName());
        dto.setLastName(receiver.getLastName());
        dto.setCin(receiver.getCin());
        dto.setBloodType(receiver.getBloodType().name());
        dto.setPhoneNumber(receiver.getTelephone());
        dto.setUrgencyLevel(receiver.getUrgentReceveur().name());
        dto.setStatusReceiver(receiver.getStatusReceiver().name());
        dto.setRequiredBags(receiver.getRequiredBags());
        dto.setReceivedBags(receiver.getReceivedBags());
        return dto;
    }

    public static Receiver toEntity(ReceiverDTO dto) {
        if (null == dto) return null;

        Receiver receiver = new Receiver();
        receiver.setId(dto.getId());
        receiver.setFirstName(dto.getFirstName());
        receiver.setLastName(dto.getLastName());
        receiver.setCin(dto.getCin());
        receiver.setBloodType(dto.getBloodType() != null ? Enum.valueOf(com.jartiste.bloodbridge.domain.enums.BloodType.class, dto.getBloodType()) : null);
        receiver.setTelephone(dto.getPhoneNumber());
        receiver.setUrgentReceveur(dto.getUrgencyLevel() != null ? Enum.valueOf(UrgentReceveur.class, dto.getUrgencyLevel()) : null);
        receiver.setStatusReceiver(dto.getStatusReceiver() != null ? Enum.valueOf(StatusReceiver.class, dto.getStatusReceiver()) : null);
        receiver.setReceivedBags(dto.getRequiredBags());
        receiver.setReceivedBags(dto.getReceivedBags());
        return receiver;
    }
}
