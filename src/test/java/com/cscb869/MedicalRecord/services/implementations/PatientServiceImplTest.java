package com.cscb869.MedicalRecord.services.implementations;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.data.enums.Specialty;
import com.cscb869.MedicalRecord.data.repository.PatientRepository;
import com.cscb869.MedicalRecord.dto.PatientDTO;
import com.cscb869.MedicalRecord.services.implementation.PatientServiceImpl;
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
public class PatientServiceImplTest {
    @Spy
    private ModelMapper modelMapper;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    private Patient patient;

    @BeforeEach
    public void init() {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Rosen");
        doctor.setMiddleName("Petrov");
        doctor.setLastName("Petrov");
        doctor.setSpecialty(Specialty.SURGERY);

        patient = new Patient();
        patient.setEgn("9495475313");
        patient.setFirstName("Nayden");
        patient.setMiddleName("Ivanov");
        patient.setLastName("Borimirov");
        patient.setDoctor(doctor);
        patient.setHasInsurance(true);
        patientRepository.save(patient);
    }

    @Test
    void getPatientById() {
        // Given
        given(patientRepository.findById(patient.getId())).willReturn(Optional.of(patient));

        // When
        PatientDTO patientDTO = patientService.getPatient(patient.getId());

        // Then
        assertThat(patientDTO.getId()).isEqualTo(patient.getId());
    }
}
