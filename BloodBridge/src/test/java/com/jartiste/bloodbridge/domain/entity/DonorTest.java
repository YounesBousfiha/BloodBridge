package com.jartiste.bloodbridge.domain.entity;

import com.jartiste.bloodbridge.domain.enums.BloodType;
import com.jartiste.bloodbridge.domain.enums.StatusDonner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DonorTest {


    private Donor donor;

    @BeforeEach
    void setUp() {
        donor = new Donor();
        donor.setId(1L);
        donor.setFirstName("John");
        donor.setLastName("Doe");
        donor.setCin("AB123456");
        donor.setTelephone("1234567890");
        donor.setBloodType(BloodType.O_MOIN);
        donor.setWeight(70);
        donor.setStatusDonneur(StatusDonner.AVAILABLE);
        donor.setDernierDon(null);
        donor.setContraindications(null);
    }

    @Test
    void testDonorCreation() {
        assertEquals(1L, donor.getId());
        assertEquals("John", donor.getFirstName());
        assertEquals("Doe", donor.getLastName());
        assertEquals("AB123456", donor.getCin());
        assertEquals("1234567890", donor.getTelephone());
        assertEquals(BloodType.O_MOIN, donor.getBloodType());
        assertEquals(70, donor.getWeight());
        assertEquals(StatusDonner.AVAILABLE, donor.getStatusDonneur());
        assertNull(donor.getDernierDon());
        assertNull(donor.getContraindications());
    }

    @Test
    void testSetters() {
        donor.setFirstName("Jane");
        donor.setLastName("Smith");
        donor.setCin("CD789012");
        donor.setTelephone("0987654321");
        donor.setBloodType(BloodType.A_PLUS);
        donor.setWeight(65);
        donor.setStatusDonneur(StatusDonner.UNAVAILABLE);

        assertEquals("Jane", donor.getFirstName());
        assertEquals("Smith", donor.getLastName());
        assertEquals("CD789012", donor.getCin());
        assertEquals("0987654321", donor.getTelephone());
        assertEquals(BloodType.A_PLUS, donor.getBloodType());
        assertEquals(65, donor.getWeight());
        assertEquals(StatusDonner.UNAVAILABLE, donor.getStatusDonneur());
    }

    @Test
    void testToString() {
        String toString = donor.toString();
        assertTrue(toString.contains("John"));
        assertTrue(toString.contains("Doe"));
        assertTrue(toString.contains("AB123456"));
        assertTrue(toString.contains("1234567890"));
        assertTrue(toString.contains("O_MOIN"));
        assertTrue(toString.contains("70"));
        assertTrue(toString.contains("AVAILABLE"));
    }

    @Test
    void testWithInEligbleWeight() {
        donor.setStatusDonneur(StatusDonner.INELIGIBLE);
        assertEquals(StatusDonner.INELIGIBLE, donor.getStatusDonneur());
    }

}
