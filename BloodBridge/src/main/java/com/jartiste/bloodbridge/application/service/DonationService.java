package com.jartiste.bloodbridge.application.service;

import com.jartiste.bloodbridge.domain.entity.Donor;
import com.jartiste.bloodbridge.domain.entity.Receiver;
import com.jartiste.bloodbridge.domain.enums.StatusDonner;
import com.jartiste.bloodbridge.domain.enums.StatusReceiver;
import com.jartiste.bloodbridge.domain.repository.DonationRepository;
import com.jartiste.bloodbridge.domain.repository.DonorRepository;
import com.jartiste.bloodbridge.domain.repository.ReceiverRepository;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import org.slf4j.Logger;

import java.util.Optional;

public class DonationService {

    private final Logger logger = AppLogger.getLogger(DonationService.class);
    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;
    private final ReceiverRepository receiverRepository;


    public DonationService(DonationRepository donationRepository, DonorRepository donorRepository, ReceiverRepository receiverRepository) {
        this.donationRepository = donationRepository;
        this.donorRepository = donorRepository;
        this.receiverRepository = receiverRepository;
    }


    // TODO: REFACTOR THIS METHOD TO BE MORE CLEAN
    public boolean associateDonorToReceiver(Long donorId, Long receiverId) {

        if (null == donorId || null == receiverId) {
            throw new IllegalArgumentException("Donor ID and Receiver ID cannot be null");
        }

        try {
            Optional<Donor> donorOpt = donorRepository.findById(donorId);
            Optional<Receiver> receiverOpt = receiverRepository.findById(receiverId);

            if (donorOpt.isEmpty()) {
                logger.error("Donor with ID {} does not exist", donorId);
                return false;
            }

            // Check if the donne has already given this receiver a bag
            if (donationRepository.hasDonorGivenToReceiver(donorId, receiverId)) {
                logger.error("Donor with ID {} has already given a bag to receiver ID {}", donorId, receiverId);
                return false;
            }

            // check if the receiver reaches his necessary bags so we can update the status of the receiver to SATISFIED
            if (receiverOpt.isPresent()) {
                Receiver receiver = receiverOpt.get();
                if (receiver.getReceivedBags() >= receiver.getRequiredBags()) {
                    receiver.setStatusReceiver(StatusReceiver.SATISFAIT);
                    receiverRepository.save(receiver);
                    logger.info("Receiver with ID {} has reached the necessary bags and is now SATISFIED", receiverId);
                    return false;
                }
            }

            if (receiverOpt.isEmpty()) {
                logger.error("Receiver with ID {} does not exist", receiverId);
                return false;
            }

            Donor donor = donorOpt.get();
            Receiver receiver = receiverOpt.get();

            // Check if the donor is AVAILABLE & dernier don has more than 1 month
            if (donor.getStatusDonneur() != StatusDonner.AVAILABLE) {
                logger.error("Donor with ID {} is not available for donation", donorId);
                return false;
            }

            // Check if the donor has donated in the last month
            if (null != donor.getDernierDon() && donor.getDernierDon().
                    plusMonths(1).isAfter(java.time.LocalDate.now())) {
                logger.error("Donor with ID {} has donated in the last month", donorId);
                return false;
            }

            // Check if the receiver Still doesn't reach his necessary bags
            if (receiver.getReceivedBags() >= receiver.getRequiredBags()) {
                logger.error("Receiver with ID {} has already received the necessary bags", receiverId);
                return false;
            }

            // Check if the blood types are compatible
            if (!donor.getBloodType().isCompatibleWith(receiver.getBloodType())) {
                logger.error("Blood type of donor ID {} is not compatible with receiver ID {}", donorId, receiverId);
                return false;
            }

            // Create the association
            boolean associated = donationRepository.associateDonorToReceiver(donorId, receiverId);

            // set the donor to UNAVAILABLE (Find a way to make this Available automatically)
            donor.setStatusDonneur(StatusDonner.UNAVAILABLE);

            // set the donor last donation date now
            donor.setDernierDon(java.time.LocalDate.now());
            donorRepository.save(donor);

            // increment the receiver received bags by 1
            receiver.setReceivedBags(receiver.getReceivedBags() + 1);
            receiverRepository.save(receiver);
            if (!associated) {
                logger.error("Failed to associate donor ID {} with receiver ID {}", donorId, receiverId);
                return false;
            }

            return true;
        } catch (Exception e) {
            logger.error("Error associating donor to receiver: ", e);
            return false;
        }
    }
}
