package com.jartiste.bloodbridge.application.service;

import com.jartiste.bloodbridge.application.mapper.ReceiverMapper;
import com.jartiste.bloodbridge.domain.entity.Receiver;
import com.jartiste.bloodbridge.domain.enums.StatusReceiver;
import com.jartiste.bloodbridge.domain.repository.ReceiverRepository;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import com.jartiste.bloodbridge.presentation.dto.ReceiverDTO;
import org.slf4j.Logger;

import java.util.List;

public class ReceiverService {

    /* Un receveur SATISFAIT ne peut plus recevoir de nouveaux donneurs */

    private final Logger logger = AppLogger.getLogger(ReceiverService.class);
    private ReceiverRepository receiverRepository;


    public ReceiverService(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }

    public ReceiverDTO createReceiver(ReceiverDTO receiverDTO) {
        try {
            Receiver receiver = ReceiverMapper.toEntity(receiverDTO);

            receiver.setStatusReceiver(StatusReceiver.EN_ATTENTE);

            Receiver newReceiver = this.receiverRepository.save(receiver);

            return ReceiverMapper.toDTO(newReceiver);
        } catch (Exception e) {
            logger.error("Error while creating receiver", e);
            return null;
        }
    }

    public ReceiverDTO updateReceiver(ReceiverDTO receiverDTO) {
        try {
            Receiver receiver = ReceiverMapper.toEntity(receiverDTO);

            Receiver updatedReceiver = this.receiverRepository.save(receiver);

            return ReceiverMapper.toDTO(updatedReceiver);
        } catch (Exception e) {
            logger.error("Error while updating receiver", e);
            return null;
        }
    }

    public boolean deleteReceiver(Long id) {

        logger.error("Deleting receiver with ID {}", id);
        try {
            if (!this.receiverRepository.isExist(id)) {
                logger.error("Receiver with ID {} does not exist", id);
                return false;
            }
            this.receiverRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error("Error while deleting receiver with ID {}", id, e);
            return false;
        }
    }

    public ReceiverDTO getReceiverById(Long id) {
        try {
            return this.receiverRepository.findById(id)
                    .map(ReceiverMapper::toDTO)
                    .orElse(null);
        } catch (Exception e) {
            logger.error("Error while fetching receiver with ID {}", id, e);
            return null;
        }
    }

    public List<ReceiverDTO> getAllReceivers() {
        try {
            // Sort them by priority: CRITIQUE -> URGENT -> NORMAL
            return this.receiverRepository.findAll()
                    .map(receivers -> receivers.stream()
                            .map(ReceiverMapper::toDTO)
                            .toList())
                    .orElse(List.of());
        } catch (Exception e) {
            logger.error("Error while fetching all receivers", e);
            return List.of();
        }
    }
}
