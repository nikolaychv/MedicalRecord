package com.cscb869.MedicalRecord.services.implementations;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.enums.Specialty;
import com.cscb869.MedicalRecord.data.repository.DoctorRepository;
import com.cscb869.MedicalRecord.dto.DoctorDTO;
import com.cscb869.MedicalRecord.services.implementation.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceImplTest {
    @Spy
    private ModelMapper modelMapper;

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    private Doctor doctor;

    @BeforeEach
    public void init() {
        doctor = new Doctor();
        doctor.setFirstName("Pavel");
        doctor.setMiddleName("Nikolov");
        doctor.setLastName("Georgiev");
        doctor.setSpecialty(Specialty.SURGERY);
        doctorRepository.save(doctor);
    }

    @Test
    void getDoctorById() {
        // Given
        given(doctorRepository.findById(doctor.getId())).willReturn(Optional.of(doctor));

        // When
        DoctorDTO doctorDTO = doctorService.getDoctor(doctor.getId());

        // Then
        assertThat(doctorDTO.getId()).isEqualTo(doctor.getId());
    }
}
