package com.jartiste.bloodbridge.infrastructure.persistence;

import com.jartiste.bloodbridge.domain.entity.Donor;
import com.jartiste.bloodbridge.domain.enums.BloodType;
import com.jartiste.bloodbridge.domain.exception.DonorRepositoryException;
import com.jartiste.bloodbridge.domain.repository.DonorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DonorRepoImplTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private DonorRepository donorRepository;

    @BeforeAll
    static void initFactory() {
        emf = Persistence.createEntityManagerFactory("testPU");
    }


    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        em = emf.createEntityManager();
        donorRepository = new DonorRepoImpl(null);

        var emField = DonorRepoImpl.class.getDeclaredField("em");
        emField.setAccessible(true);
        emField.set(donorRepository, em);
    }

    @AfterEach
    void tearDown() {
        em.close();
    }

    @AfterAll
    static void closeFactory() {
        emf.close();
    }

    @Test
    void saveTest() {
        Donor donor = new Donor();
        donor.setFirstName("John");
        donor.setLastName("Doe");
        donor.setCin("CIN123456");
        donor.setBloodType(BloodType.AB_PLUS);
        donor.setTelephone("123456789");
        donor.setWeight(70);
        donor.setDateDeNaissance(java.time.LocalDate.of(1990, 1, 1));

        Donor newDonor = donorRepository.save(donor);


        assertNotNull(newDonor.getId());
        assertEquals("John", newDonor.getFirstName());
        assertEquals("Doe", newDonor.getLastName());
        assertEquals("CIN123456", newDonor.getCin());
        assertEquals(BloodType.AB_PLUS, newDonor.getBloodType());
        assertEquals("123456789", newDonor.getTelephone());
        assertEquals(70, newDonor.getWeight());
        assertEquals(java.time.LocalDate.of(1990, 1, 1), newDonor.getDateDeNaissance());
    }

    @Test
    void saveTest_Exception() {
        Donor donor = new Donor();
        donor.setFirstName("Jane");
        donor.setLastName("Doe");
        donor.setCin("CIN654321");
        donor.setBloodType(BloodType.O_PLUS);
        donor.setTelephone("987654321");
        donor.setWeight(60);
        donor.setDateDeNaissance(java.time.LocalDate.of(1992, 2, 2));

        em.close();

        Assertions.assertThrows(DonorRepositoryException.class, () -> {
            donorRepository.save(donor);
        });
    }

    @Test
    void findByIdTest() {
        Donor donor = new Donor();
        donor.setFirstName("Alice");
        donor.setLastName("Smith");
        donor.setCin("CIN789012");
        donor.setBloodType(BloodType.B_MOIN);
        donor.setTelephone("555555555");
        donor.setWeight(65);
        donor.setDateDeNaissance(java.time.LocalDate.of(1985, 5, 5));

        Donor savedDonor = donorRepository.save(donor);

        Donor foundDonor = donorRepository.findById(savedDonor.getId()).orElse(null);

        assertNotNull(foundDonor);
        assertEquals("Alice", foundDonor.getFirstName());
        assertEquals("Smith", foundDonor.getLastName());
        assertEquals("CIN789012", foundDonor.getCin());
        assertEquals(BloodType.B_MOIN, foundDonor.getBloodType());
        assertEquals("555555555", foundDonor.getTelephone());
        assertEquals(65, foundDonor.getWeight());
        assertEquals(java.time.LocalDate.of(1985, 5, 5), foundDonor.getDateDeNaissance());
    }

    @Test
    void findByIdTest_NotFound() {
        Donor foundDonor = donorRepository.findById(9999L).orElse(null);
        assertNull(foundDonor);
    }

    @Test
    void findByIdTest_Exception() {
        em.close();

        Assertions.assertThrows(DonorRepositoryException.class, () -> {
            donorRepository.findById(1L);
        });
    }

}
