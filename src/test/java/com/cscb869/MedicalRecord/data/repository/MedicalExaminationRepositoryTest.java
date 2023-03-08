package com.cscb869.MedicalRecord.data.repository;

import com.cscb869.MedicalRecord.data.entity.*;
import com.cscb869.MedicalRecord.data.enums.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.MethodInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class MedicalExaminationRepositoryTest {
    @Autowired
    private MedicalExaminationRepository medicalExaminationRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private MedicalExamination medicalExamination;

    @BeforeEach
    public void setup() {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Atanas");
        doctor.setMiddleName("Ivanov");
        doctor.setLastName("Atanasov");
        doctor.setSpecialty(Specialty.SURGERY);
        testEntityManager.persistAndFlush(doctor);

        Patient patient = new Patient();
        patient.setEgn("8975687653");
        patient.setFirstName("Krasimir");
        patient.setMiddleName("Nikolov");
        patient.setLastName("Angelov");
        patient.setDoctor(doctor);
        patient.setHasInsurance(true);
        testEntityManager.persistAndFlush(patient);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setName("Grip");
        diagnosis.setDescription("Description 1");
        testEntityManager.persistAndFlush(diagnosis);

        SickSheet sickSheet = new SickSheet();
        sickSheet.setFromDate(LocalDate.of(2024, 3, 5));
        sickSheet.setToDate(LocalDate.of(2024, 3, 12));
        sickSheet.setDescription("Description 4");
        testEntityManager.persistAndFlush(sickSheet);

        medicalExamination = new MedicalExamination();
        medicalExamination.setPatient(patient);
        medicalExamination.setDoctor(doctor);
        medicalExamination.setExaminationDate(LocalDate.of(2024, 3, 6));
        medicalExamination.setDiagnosis(diagnosis);
        medicalExamination.setTreatment("Antibiotic");
        medicalExamination.setNeedsSickSheet(true);
        medicalExamination.setSickSheet(sickSheet);
        testEntityManager.persistAndFlush(medicalExamination);
    }

    @Test
    void getMedicalExaminationByIdTest() {
        Optional<MedicalExamination> medicalExaminationById =
                medicalExaminationRepository.findById(medicalExamination.getId());
        assertTrue(medicalExaminationById.isPresent());
    }

    @Test
    void findAllByPatientTest() {
        Doctor doctor2 = new Doctor();
        doctor2.setFirstName("Yoan");
        doctor2.setMiddleName("Georgiev");
        doctor2.setLastName("Petrov");
        doctor2.setSpecialty(Specialty.SURGERY);
        testEntityManager.persistAndFlush(doctor2);

        Patient patient2 = new Patient();
        patient2.setEgn("9784638539");
        patient2.setFirstName("Kaloyan");
        patient2.setMiddleName("Nikolov");
        patient2.setLastName("Canov");
        patient2.setDoctor(doctor2);
        patient2.setHasInsurance(true);
        testEntityManager.persistAndFlush(patient2);

        Diagnosis diagnosis2 = new Diagnosis();
        diagnosis2.setName("Grip B");
        diagnosis2.setDescription("Description 2");
        testEntityManager.persistAndFlush(diagnosis2);

        SickSheet sickSheet2 = new SickSheet();
        sickSheet2.setFromDate(LocalDate.of(2024, 2, 5));
        sickSheet2.setToDate(LocalDate.of(2024, 2, 12));
        sickSheet2.setDescription("Description 3");
        testEntityManager.persistAndFlush(sickSheet2);

        MedicalExamination medicalExamination2 = new MedicalExamination();
        medicalExamination2.setPatient(patient2);
        medicalExamination2.setDoctor(doctor2);
        medicalExamination2.setExaminationDate(LocalDate.of(2024, 2, 5));
        medicalExamination2.setDiagnosis(diagnosis2);
        medicalExamination2.setTreatment("Antibiotic");
        medicalExamination2.setNeedsSickSheet(true);
        medicalExamination2.setSickSheet(sickSheet2);
        testEntityManager.persistAndFlush(medicalExamination2);

        MedicalExamination medicalExamination3 = new MedicalExamination();
        medicalExamination3.setPatient(patient2);
        medicalExamination3.setDoctor(doctor2);
        medicalExamination3.setExaminationDate(LocalDate.of(2024, 2, 5));
        medicalExamination3.setDiagnosis(diagnosis2);
        medicalExamination3.setTreatment("Antibiotic");
        medicalExamination3.setNeedsSickSheet(true);
        medicalExamination3.setSickSheet(sickSheet2);
        testEntityManager.persistAndFlush(medicalExamination3);

        List<MedicalExamination> medicalExaminationList = Arrays.asList(medicalExamination2, medicalExamination3);

        assertIterableEquals(medicalExaminationList,
               medicalExaminationRepository.findAllByPatient(patient2));
    }

    @Test
    void findAllByEmptyTreatmentTest() {

        assertThat(medicalExaminationRepository.findAllByTreatment("").size()).isEqualTo(0);
    }

    @Test
    void findAllByNotFoundTreatmentTest() {

        assertThat(medicalExaminationRepository.findAllByTreatment("Treatment 28").size()).isEqualTo(0);
    }

    @Test
    void updateMedicalExaminationTreatmentTest() {
        medicalExamination.setTreatment("Treatment 7");
        medicalExaminationRepository.save(medicalExamination);

        assertThat(medicalExamination.getTreatment()).isEqualTo("Treatment 7");
    }

    @Test
    void deleteMedicalExaminationTest() {
        medicalExaminationRepository.deleteById(medicalExamination.getId());
        Optional<MedicalExamination> deletedMedicalExamination =
                medicalExaminationRepository.findById(medicalExamination.getId());

        assertTrue(deletedMedicalExamination.isEmpty());
    }
}
