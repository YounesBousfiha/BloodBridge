package com.jartiste.bloodbridge.domain.repository;

import com.jartiste.bloodbridge.domain.entity.Donor;

import java.util.List;
import java.util.Optional;

public interface DonorRepository {
    Donor save(Donor donor);
    Optional<Donor> findById(Long id);
    void deleteById(Long id);
    Optional<List<Donor>> findAll();
    boolean isExist(Long id);
}
