package com.jartiste.bloodbridge.domain.repository;

import com.jartiste.bloodbridge.domain.entity.Receiver;

import java.util.List;
import java.util.Optional;

public interface ReceiverRepository {
    Receiver save(Receiver receiver);
    Optional<Receiver> findById(Long id);
    void deleteById(Long id);
    Optional<List<Receiver>> findAll();
    boolean isExist(Long id);
}
