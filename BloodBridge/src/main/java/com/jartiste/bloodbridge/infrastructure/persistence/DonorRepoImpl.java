package com.jartiste.bloodbridge.infrastructure.persistence;

import com.jartiste.bloodbridge.domain.entity.Donor;
import com.jartiste.bloodbridge.domain.exception.DonorRepositoryException;
import com.jartiste.bloodbridge.domain.repository.DonorRepository;
import com.jartiste.bloodbridge.infrastructure.config.EntityMangerConf;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


public class DonorRepoImpl implements DonorRepository {

    /* CHOF PLANIFICATION A BNADME 3AD KML */

    private final EntityManager em = EntityMangerConf.getEmf().createEntityManager();

    private final Logger logger = AppLogger.getLogger(DonorRepoImpl.class);

    private DataSource dataSource;

    public DonorRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Donor save(Donor donor) {
        try {
            em.getTransaction().begin();
            Donor managedDonor = em.merge(donor);
            em.getTransaction().commit();
            return managedDonor;
        } catch (Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                logger.info("Transaction for saving [{}] has been rolled back", donor.getId());
            }
            throw new DonorRepositoryException("Error saving donor", e);
        }
    }

    @Override
    public Optional<Donor> findById(Long id) {
        try {
            Donor donor = em.find(Donor.class, id);
            return Optional.ofNullable(donor);
        } catch (Exception e) {
            throw new DonorRepositoryException("Error finding donor by id", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            em.getTransaction().begin();
            Donor donor = em.find(Donor.class, id);
            if(null != donor) {
                em.remove(donor);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                logger.info("Transaction  for deleting [{}] has been rolled back", id);
            }
            throw new DonorRepositoryException("Error deleting donor by id", e);
        }
    }

    @Override
    public Optional<List<Donor>> findAll() {
        try {
            List<Donor> donors = em.createQuery("SELECT d FROM Donor d", Donor.class).getResultList();
            return Optional.of(donors);
        } catch (Exception e) {
            throw new DonorRepositoryException("Error find all donors", e);
        }
    }
}
