package com.jartiste.bloodbridge.domain.entity;

import com.jartiste.bloodbridge.domain.enums.BloodType;
import com.jartiste.bloodbridge.domain.enums.StatusReceiver;
import com.jartiste.bloodbridge.domain.enums.UrgentReceveur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReceiverTest {

    private Receiver receiver;


    @BeforeEach
    void setUp() {
        receiver = new Receiver();
        receiver.setId(1L);
        receiver.setFirstName("Alice");
        receiver.setLastName("Johnson");
        receiver.setCin("XY987654");
        receiver.setTelephone("1122334455");
        receiver.setDateDeNaissance(LocalDate.of(1990, 5, 15));
        receiver.setBloodType(BloodType.B_PLUS);
        receiver.setReceivedBags(2);
        receiver.setStatusReceiver(StatusReceiver.EN_ATTENTE);
        receiver.setUrgentReceveur(UrgentReceveur.CRITICAL);
    }


    @Test
    void testReceiverCreation() {
        assertEquals(1L, receiver.getId());
        assertEquals("Alice", receiver.getFirstName());
        assertEquals("Johnson", receiver.getLastName());
        assertEquals("XY987654", receiver.getCin());
        assertEquals("1122334455", receiver.getTelephone());
        assertEquals(LocalDate.of(1990, 5, 15), receiver.getDateDeNaissance());
        assertEquals(BloodType.B_PLUS, receiver.getBloodType());
        assertEquals(2, receiver.getReceivedBags());
        assertEquals(UrgentReceveur.CRITICAL, receiver.getUrgentReceveur());
        assertEquals(4, receiver.getRequiredBags());
        assertEquals(StatusReceiver.EN_ATTENTE, receiver.getStatusReceiver());
    }

    @Test
    void testSetters() {
        receiver.setFirstName("Bob");
        receiver.setLastName("Smith");
        receiver.setCin("ZZ123456");
        receiver.setTelephone("5566778899");
        receiver.setDateDeNaissance(LocalDate.of(1985, 10, 20));
        receiver.setBloodType(BloodType.A_MOIN);
        receiver.setReceivedBags(1);
        receiver.setUrgentReceveur(UrgentReceveur.NORMAL);
        receiver.setStatusReceiver(StatusReceiver.SATISFAIT);

        assertEquals("Bob", receiver.getFirstName());
        assertEquals("Smith", receiver.getLastName());
        assertEquals("ZZ123456", receiver.getCin());
        assertEquals("5566778899", receiver.getTelephone());
        assertEquals(LocalDate.of(1985, 10, 20), receiver.getDateDeNaissance());
        assertEquals(BloodType.A_MOIN, receiver.getBloodType());
        assertEquals(1, receiver.getReceivedBags());
        assertEquals(UrgentReceveur.NORMAL, receiver.getUrgentReceveur());
        assertEquals(1, receiver.getRequiredBags());
        assertEquals(StatusReceiver.SATISFAIT, receiver.getStatusReceiver());
    }


    @Test
    void testToString() {
        String toString = receiver.toString();
        assertTrue(toString.contains("Alice"));
        assertTrue(toString.contains("Johnson"));
        assertTrue(toString.contains("XY987654"));
        assertTrue(toString.contains("1122334455"));
        assertTrue(toString.contains("1990-05-15"));
        assertTrue(toString.contains("B_PLUS"));
        assertTrue(toString.contains("2"));
        assertTrue(toString.contains("CRITICAL"));
        assertTrue(toString.contains("EN_ATTENTE"));
    }
}
