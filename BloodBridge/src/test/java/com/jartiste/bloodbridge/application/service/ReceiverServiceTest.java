package com.jartiste.bloodbridge.application.service;

import com.jartiste.bloodbridge.application.mapper.ReceiverMapper;
import com.jartiste.bloodbridge.domain.entity.Receiver;
import com.jartiste.bloodbridge.domain.enums.BloodType;
import com.jartiste.bloodbridge.domain.enums.StatusReceiver;
import com.jartiste.bloodbridge.domain.enums.UrgentReceveur;
import com.jartiste.bloodbridge.domain.repository.ReceiverRepository;
import com.jartiste.bloodbridge.presentation.dto.ReceiverDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class ReceiverServiceTest {
    private ReceiverDTO dto;

    private Receiver receiver;


    @Mock
    private ReceiverRepository receiverRepository;

    private ReceiverService receiverService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        dto = new ReceiverDTO();
        dto.setId(1L);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setCin("12345678");
        dto.setBloodType(BloodType.O_PLUS);
        dto.setPhoneNumber("06112233445");
        dto.setUrgencyLevel(UrgentReceveur.CRITICAL);
        dto.setStatusReceiver(StatusReceiver.EN_ATTENTE);
        dto.setRequiredBags(4);
        dto.setReceivedBags(0);


        receiver = ReceiverMapper.toEntity(dto);
        receiverService = new ReceiverService(receiverRepository);
    }


    @Test
    void testCreateReceiver(){
        when(receiverRepository.save(any(Receiver.class))).thenReturn(receiver);

        dto = receiverService.createReceiver(dto);

        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("12345678", dto.getCin());
        assertEquals(BloodType.O_PLUS, dto.getBloodType());
        assertEquals("06112233445", dto.getPhoneNumber());
        assertEquals(UrgentReceveur.CRITICAL, dto.getUrgencyLevel());
        assertEquals(StatusReceiver.EN_ATTENTE, dto.getStatusReceiver());
        assertEquals(4, dto.getRequiredBags());
        assertEquals(0, dto.getReceivedBags());
    }

    @Test
    void testCreateReceiver_Exception(){
        when(receiverRepository.save(any(Receiver.class))).thenThrow(new RuntimeException("Database error"));

        dto = receiverService.createReceiver(dto);

        assertNull(dto);
    }

    @Test
    void testUpdateReceiver(){

        receiver.setFirstName("Hassan");

        when(receiverRepository.save(any(Receiver.class))).thenReturn(receiver);

        dto.setFirstName("Hassan");

        dto = receiverService.updateReceiver(dto);

        assertEquals("Hassan", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("12345678", dto.getCin());
        assertEquals(BloodType.O_PLUS, dto.getBloodType());
        assertEquals("06112233445", dto.getPhoneNumber());
        assertEquals(UrgentReceveur.CRITICAL, dto.getUrgencyLevel());
        assertEquals(StatusReceiver.EN_ATTENTE, dto.getStatusReceiver());
        assertEquals(4, dto.getRequiredBags());
        assertEquals(0, dto.getReceivedBags());

    }

    @Test
    void testUpdateReceiver_Exception(){
        when(receiverRepository.save(any(Receiver.class))).thenThrow(new RuntimeException("Database error"));

        dto = receiverService.updateReceiver(dto);

        assertNull(dto);
    }


    @Test
    void testDeleteReceiver(){
        when(receiverRepository.isExist(1L)).thenReturn(true);

        boolean result = receiverService.deleteReceiver(1L);

        assertTrue(result);
    }

    @Test
    void testDeleteReceiver_Exception(){
        doNothing().when(receiverRepository).deleteById(1L);

        when(receiverRepository.isExist(1L)).thenReturn(false);

        boolean result = receiverService.deleteReceiver(1L);

        assertFalse(result);
    }

    @Test
    void testGetAllReceivers(){
        when(receiverRepository.findAll()).thenReturn(Optional.of(List.of(receiver)));

        List<ReceiverDTO> result  = receiverService.getAllReceivers();

        assertFalse(result.isEmpty());
    }

    @Test
    void testGetAllReceiversWhenEmpty() {
        when(receiverRepository.findAll()).thenReturn(Optional.empty());

        List<ReceiverDTO> result = receiverService.getAllReceivers();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllReceiversWhenExceptionOccurs() {
        when(receiverRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        List<ReceiverDTO> result = receiverService.getAllReceivers();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetReceiverById(){

        when(receiverRepository.isExist(1L)).thenReturn(true);
        when(receiverRepository.findById(1L)).thenReturn(Optional.of(receiver));

        var result  = receiverService.getReceiverById(1L);

        assertEquals("John", result.getFirstName());
    }

    @Test
    void testGetReceiverByIdWhenNotFound() {
        when(receiverRepository.findById(1L)).thenReturn(Optional.empty());

        ReceiverDTO result = receiverService.getReceiverById(1L);

        assertNull(result);
    }

    @Test
    void testGetReceiverByIdWhenExceptionOccurs() {
        when(receiverRepository.findById(1L)).thenThrow(new RuntimeException("Database error"));

        ReceiverDTO result = receiverService.getReceiverById(1L);

        assertNull(result);
    }
}
