package com.jartiste.bloodbridge.domain.repository;

public interface DonationRepository {
    boolean associateDonorToReceiver(Long donorId, Long receiverId);
    boolean hasDonorGivenToReceiver(Long donorId, Long receiverId);
}
