package com.jartiste.bloodbridge.infrastructure.persistence;

import com.jartiste.bloodbridge.domain.entity.Receiver;
import com.jartiste.bloodbridge.domain.exception.ReceiverRepositoryException;
import com.jartiste.bloodbridge.domain.repository.ReceiverRepository;
import com.jartiste.bloodbridge.infrastructure.config.EntityMangerConf;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ReceiverRepoImpl  implements ReceiverRepository {

    private final EntityManager em = EntityMangerConf.getEmf().createEntityManager();
    private final Logger logger = AppLogger.getLogger(ReceiverRepoImpl.class);

    private final DataSource dataSource;

    public ReceiverRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Receiver save(Receiver receiver) {
        try {
            em.getTransaction().begin();
            Receiver managedReceiver = em.merge(receiver);
            em.getTransaction().commit();

            return managedReceiver;
        } catch (Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                logger.info("Transaction for Saving Receiver [{}] has been rolled back", receiver.getId());
            }
            throw new ReceiverRepositoryException("Error saving Receiver", e);
        }
    }

    @Override
    public Optional<Receiver> findById(Long id) {
        try {
            Receiver receiver = em.find(Receiver.class, id);
            return Optional.ofNullable(receiver);
        } catch (Exception e) {
            throw new ReceiverRepositoryException("Error finding donor by id", e);
        }
    }

    @Override
    public void deleteById(Long id) {
       try {
           em.getTransaction().begin();
           Receiver receiver = em.find(Receiver.class, id);
           logger.error("Deleting Receiver: {}", receiver);
           if(null != receiver) {
               em.remove(receiver);
           }
           em.getTransaction().commit();
       } catch (Exception e) {
           if(em.getTransaction().isActive()) {
               em.getTransaction().rollback();
               logger.info("Transaction for deleting Receiver [{}] has been rolled back", id);
           }
           throw new ReceiverRepositoryException("Error deleting receiver by id", e);
       }
    }

    @Override
    public Optional<List<Receiver>> findAll() {
        try {
            List<Receiver> receivers = em.createQuery("SELECT d FROM Receiver d", Receiver.class).getResultList();
            return Optional.ofNullable(receivers);
        } catch (Exception e) {
            throw new ReceiverRepositoryException("Error find all receiver ", e);
        }
    }

    public boolean isExist(Long id) {
        try {
            Receiver receiver = em.find(Receiver.class, id);
            return null != receiver;
        } catch (Exception e) {
            throw  new ReceiverRepositoryException("Error while Checking isExist", e);
        }
    }
}
