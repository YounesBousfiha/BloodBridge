package com.jartiste.bloodbridge.infrastructure.persistence;

import com.jartiste.bloodbridge.domain.exception.DonationRepoException;
import com.jartiste.bloodbridge.domain.repository.DonationRepository;
import com.jartiste.bloodbridge.infrastructure.config.EntityMangerConf;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;

import javax.sql.DataSource;

public class DonationRepoImpl implements DonationRepository {


    private static final Logger logger = AppLogger.getLogger(DonationRepoImpl.class);
    private static final EntityManager em = EntityMangerConf.getEmf().createEntityManager();

    private DataSource dataSource;

    public DonationRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean associateDonorToReceiver(Long donorId, Long receiverId) {
        try {
            em.getTransaction().begin();

            em.createNativeQuery("INSERT INTO donations (donor_id, receiver_id) VALUES (?, ?)")
                    .setParameter(1, donorId)
                    .setParameter(2, receiverId)
                    .executeUpdate();
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                logger.info("Transaction to associate donor [{}] to receiver [{}] has been rolled back", donorId, receiverId);
            }
            throw new DonationRepoException("Error associating donor to receiver", e);
        }
    }

    @Override
    public boolean hasDonorGivenToReceiver(Long donorId, Long receiverId) {
        try {
            Long count = (Long) em.createNativeQuery("SELECT COUNT(*) FROM donations WHERE donor_id = ? AND receiver_id = ?")
                    .setParameter(1, donorId)
                    .setParameter(2, receiverId)
                    .getSingleResult();

            return count != null && count > 0;
        } catch (Exception e) {
            throw new DonationRepoException("Error checking if donor has given to receiver", e);
        }
    }
}
