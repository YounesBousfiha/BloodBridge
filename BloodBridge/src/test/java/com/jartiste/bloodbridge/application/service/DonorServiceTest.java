package com.jartiste.bloodbridge.application.service;

import com.jartiste.bloodbridge.application.mapper.DonorMapper;
import com.jartiste.bloodbridge.application.validation.DonorRequiredFieldsValidator;
import com.jartiste.bloodbridge.domain.entity.Donor;
import com.jartiste.bloodbridge.domain.enums.BloodType;
import com.jartiste.bloodbridge.domain.enums.Contraindication;
import com.jartiste.bloodbridge.domain.enums.StatusDonner;
import com.jartiste.bloodbridge.domain.exception.DonorNotExists;
import com.jartiste.bloodbridge.domain.exception.InvalidDonorException;
import com.jartiste.bloodbridge.domain.repository.DonorRepository;
import com.jartiste.bloodbridge.domain.repository.ReceiverRepository;
import com.jartiste.bloodbridge.presentation.dto.DonorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class DonorServiceTest {

    private DonorDTO dto;
    private Donor donor;

    @Mock
    private DonorRepository donorRepository;

    @Mock
    private ReceiverRepository receiverRepository;

    private DonorService donorService;

    @BeforeEach
    void setUp() {
        dto = new DonorDTO();
        dto.setId(1L);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setCin("12345678");
        dto.setBloodType(BloodType.O_MOIN);
        dto.setTelephone("06112233445");
        dto.setWeight(70);
        dto.setDateDeNaissance(LocalDate.of(1995, 12, 3));
        dto.setCreatedAt(LocalDate.now());
        dto.setContraindications(Set.of(Contraindication.EPILEPSIE, Contraindication.GREFFE, Contraindication.CARDIAC_DISEASE));
        dto.setStatusDonneur(StatusDonner.INELIGIBLE);
        dto.setDernierDon(null);

        donor = DonorMapper.toEntity(dto);
        MockitoAnnotations.openMocks(this);
        donorService = new DonorService(donorRepository, receiverRepository);
    }

    @Test
    void testCreateDonor() {
        when(this.donorRepository.save(any(Donor.class))).thenReturn(donor);

        boolean result  = donorService.createDonor(dto);

        assertTrue(result);
    }

    @Test
    void testCreateDonorThrowsException() {
        try (MockedStatic<DonorRequiredFieldsValidator> mockedValidator = mockStatic(DonorRequiredFieldsValidator.class)) {
            mockedValidator.when(() -> DonorRequiredFieldsValidator.validate(any(DonorDTO.class)))
                    .thenThrow(new InvalidDonorException("Validation failed"));

            boolean result = donorService.createDonor(dto);

            assertFalse(result);
        }

    }

    @Test
    void testUpdateDonor() {
        when(this.donorRepository.save(any(Donor.class))).thenReturn(donor);

        DonorDTO result  = donorService.updateDonor(dto);

        verify(this.donorRepository, times(1)).save(any(Donor.class));
    }


    @Test
    void testGetAllDonors() {
        when(this.donorRepository.findAll()).thenReturn(Optional.of(List.of(donor)));

        List<DonorDTO> result  = donorService.findAllDonors();

        assertFalse(result.isEmpty());
    }


    @Test
    void testGetDonorById() {
        when(this.donorRepository.findById(1L)).thenReturn(Optional.of(donor));

        var result  = donorService.findDonorbyId(String.valueOf(1L));

        assertEquals("John", result.getFirstName());
    }

    @Test
    void testFindDonorByIdThrowsException() {
        when(this.donorRepository.findById(1L)).thenReturn(Optional.empty());

        DonorDTO donorDTO = donorService.findDonorbyId(String.valueOf(1L));

        assertNull(donorDTO);

    }

    @Test
    void testDeleteDonor() {
        when(this.donorRepository.isExist(1L)).thenReturn(true);
        doNothing().when(this.donorRepository).deleteById(1L);

        boolean result = donorService.deleteDonor("1");

        assertTrue(result);
        verify(this.donorRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteDonorWithNonExisantId() {
        when(this.donorRepository.isExist(2L)).thenReturn(false);
        String id = String.valueOf(2L);
        assertThrows(DonorNotExists.class, () -> donorService.deleteDonor(id));
    }

    @Test
    void testDeleteNonExistingDonor() {
        when(this.donorRepository.isExist(2L)).thenReturn(false);

        String id = String.valueOf(2L);

        assertThrows(DonorNotExists.class, () -> {
            donorService.deleteDonor(String.valueOf(id));
        });
    }
}
