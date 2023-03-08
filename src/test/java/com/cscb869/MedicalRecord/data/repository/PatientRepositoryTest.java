package com.cscb869.MedicalRecord.data.repository;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.data.enums.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PatientRepositoryTest {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Patient patient;

    @BeforeEach
    public void setup() {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Rosen");
        doctor.setMiddleName("Petrov");
        doctor.setLastName("Petrov");
        doctor.setSpecialty(Specialty.SURGERY);
        testEntityManager.persistAndFlush(doctor);

        patient = new Patient();
        patient.setEgn("9495475313");
        patient.setFirstName("Nayden");
        patient.setMiddleName("Ivanov");
        patient.setLastName("Borimirov");
        patient.setDoctor(doctor);
        patient.setHasInsurance(true);
        testEntityManager.persistAndFlush(patient);
    }

    @Test
    void getPatientByIdTest() {
        Optional<Patient> patientById = patientRepository.findById(patient.getId());
        assertTrue(patientById.isPresent());
    }

    @Test
    void findAllByLastNameTest() {
        String lastName = "Nikolov";
        Doctor doctor2 = new Doctor();
        doctor2.setFirstName("Martin");
        doctor2.setMiddleName("Vasilev");
        doctor2.setLastName("Cholakov");
        doctor2.setSpecialty(Specialty.ANESTHESIOLOGY);
        testEntityManager.persistAndFlush(doctor2);

        Patient patient2 = new Patient();
        patient2.setEgn("6858946279");
        patient2.setFirstName("Pavel");
        patient2.setMiddleName("Yordanov");
        patient2.setLastName(lastName);
        patient2.setDoctor(doctor2);
        patient2.setHasInsurance(true);
        testEntityManager.persistAndFlush(patient2);

        Patient patient3 = new Patient();
        patient3.setEgn("5892658025");
        patient3.setFirstName("Ivan");
        patient3.setMiddleName("Ivanov");
        patient3.setLastName(lastName);
        patient3.setDoctor(doctor2);
        patient3.setHasInsurance(false);
        testEntityManager.persistAndFlush(patient3);

        List<Patient> patientList = Arrays.asList(patient2, patient3);

        assertIterableEquals(patientList, patientRepository.findAllByLastName(lastName));
    }

    @Test
    void findAllByFirstNameAndLastNameTest() {
        String firstName = "Alexander";
        String lastName = "Georgiev";
        Doctor doctor2 = new Doctor();
        doctor2.setFirstName("Iliyan");
        doctor2.setMiddleName("Yordanov");
        doctor2.setLastName("Genchev");
        doctor2.setSpecialty(Specialty.INFECTIOUS_DISEASES);
        testEntityManager.persistAndFlush(doctor2);

        Patient patient2 = new Patient();
        patient2.setEgn("8967853468");
        patient2.setFirstName(firstName);
        patient2.setMiddleName("Momchilov");
        patient2.setLastName(lastName);
        patient2.setDoctor(doctor2);
        patient2.setHasInsurance(true);
        testEntityManager.persistAndFlush(patient2);

        Patient patient3 = new Patient();
        patient3.setEgn("8769627894");
        patient3.setFirstName(firstName);
        patient3.setMiddleName("Ivanov");
        patient3.setLastName(lastName);
        patient3.setDoctor(doctor2);
        patient3.setHasInsurance(true);
        testEntityManager.persistAndFlush(patient3);

        List<Patient> patientList = Arrays.asList(patient2, patient3);

        assertIterableEquals(patientList, patientRepository.findAllByFirstNameAndLastName(firstName, lastName));
    }

    @Test
    void findAllByEmptyLastNameTest() {

        assertThat(patientRepository.findAllByLastName("").size()).isEqualTo(0);
    }

    @Test
    void findAllByNotFoundLastNameTest() {

        assertThat(patientRepository.findAllByLastName("Nikola").size()).isEqualTo(0);
    }

    @Test
    void updatePatientLastNameTest() {
        patient.setLastName("Ivanov");
        patientRepository.save(patient);

        assertThat(patient.getLastName()).isEqualTo("Ivanov");
    }

    @Test
    void deletePatientTest() {
        patientRepository.deleteById(patient.getId());
        Optional<Patient> deletedPatient = patientRepository.findById(patient.getId());

        assertTrue(deletedPatient.isEmpty());
    }
}