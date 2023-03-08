package com.cscb869.MedicalRecord.services.implementations;

import com.cscb869.MedicalRecord.data.entity.Diagnosis;
import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.data.enums.Specialty;
import com.cscb869.MedicalRecord.data.repository.MedicalExaminationRepository;
import com.cscb869.MedicalRecord.dto.MedicalExaminationDTO;
import com.cscb869.MedicalRecord.services.implementation.MedicalExaminationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MedicalExaminationServiceImplTest {
    @Spy
    private ModelMapper modelMapper;

    @Mock
    private MedicalExaminationRepository medicalExaminationRepository;

    @InjectMocks
    private MedicalExaminationServiceImpl medicalExaminationService;

    private MedicalExamination medicalExamination;

    @BeforeEach
    public void init() {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Atanas");
        doctor.setMiddleName("Ivanov");
        doctor.setLastName("Atanasov");
        doctor.setSpecialty(Specialty.SURGERY);

        Patient patient = new Patient();
        patient.setEgn("8975687653");
        patient.setFirstName("Krasimir");
        patient.setMiddleName("Nikolov");
        patient.setLastName("Angelov");
        patient.setDoctor(doctor);
        patient.setHasInsurance(true);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setName("Grip");
        diagnosis.setDescription("Description 1");

        medicalExamination = new MedicalExamination();
        medicalExamination.setPatient(patient);
        medicalExamination.setDoctor(doctor);
        medicalExamination.setExaminationDate(LocalDate.of(2023, 3, 6));
        medicalExamination.setDiagnosis(diagnosis);
        medicalExamination.setTreatment("Antibiotic");
        medicalExamination.setNeedsSickSheet(true);
        medicalExaminationRepository.save(medicalExamination);
    }

    @Test
    void getMedicalExaminationById() {
        // Given
        given(medicalExaminationRepository.findById(medicalExamination.getId())).
                willReturn(Optional.of(medicalExamination));

        // When
        MedicalExaminationDTO medicalExaminationDTO = medicalExaminationService.
                getMedicalExamination(medicalExamination.getId());

        // Then
        assertThat(medicalExaminationDTO.getId()).isEqualTo(medicalExamination.getId());
    }
}
