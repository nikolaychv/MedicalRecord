package com.cscb869.MedicalRecord.data.repository;

import com.cscb869.MedicalRecord.data.entity.Doctor;
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
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Doctor doctor;

    @BeforeEach
    public void setup() {
        doctor = new Doctor();
        doctor.setFirstName("Pavel");
        doctor.setMiddleName("Nikolov");
        doctor.setLastName("Georgiev");
        doctor.setSpecialty(Specialty.SURGERY);
        testEntityManager.persistAndFlush(doctor);
    }

    @Test
    void getDoctorByIdTest() {
        Optional<Doctor> doctorById = doctorRepository.findById(doctor.getId());
        assertTrue(doctorById.isPresent());
    }

    @Test
    void findAllByFirstNameTest() {
        String firstName = "Nikola";
        Doctor doctor2 = new Doctor();
        doctor2.setFirstName(firstName);
        doctor2.setMiddleName("Ivanov");
        doctor2.setLastName("Pavlov");
        doctor2.setSpecialty(Specialty.CARDIOLOGY);
        testEntityManager.persistAndFlush(doctor2);

        Doctor doctor3 = new Doctor();
        doctor3.setFirstName(firstName);
        doctor3.setMiddleName("Nikolov");
        doctor3.setLastName("Stoyanov");
        doctor3.setSpecialty(Specialty.GENERAL_MEDICINE);
        testEntityManager.persistAndFlush(doctor3);

        List<Doctor> doctorList = Arrays.asList(doctor2, doctor3);

        assertIterableEquals(doctorList, doctorRepository.findAllByFirstName(firstName));
    }

    @Test
    void findAllByFirstNameAndLastNameTest() {
        String firstName = "Atanas";
        String lastName = "Petkanov";
        Doctor doctor2 = new Doctor();
        doctor2.setFirstName(firstName);
        doctor2.setMiddleName("Ivanov");
        doctor2.setLastName(lastName);
        doctor2.setSpecialty(Specialty.CARDIOLOGY);
        testEntityManager.persistAndFlush(doctor2);

        Doctor doctor3 = new Doctor();
        doctor3.setFirstName(firstName);
        doctor3.setMiddleName("Nikolov");
        doctor3.setLastName(lastName);
        doctor3.setSpecialty(Specialty.GENERAL_MEDICINE);
        testEntityManager.persistAndFlush(doctor3);

        List<Doctor> doctorList = Arrays.asList(doctor2, doctor3);

        assertIterableEquals(doctorList, doctorRepository.findAllByFirstNameAndLastName(firstName, lastName));
    }

    @Test
    void findAllByEmptyFirstNameTest() {
        assertThat(doctorRepository.findAllByFirstName("").size()).isEqualTo(0);
    }

    @Test
    void findAllByNotFoundFirstNameTest() {
        assertThat(doctorRepository.findAllByFirstName("Tsvetan").size()).isEqualTo(0);
    }

    @Test
    void saveDoctorTest() {
        Doctor doctor2 = new Doctor();
        doctor2.setFirstName("Atanas");
        doctor2.setMiddleName("Atanasov");
        doctor2.setLastName("Atanasov");
        doctor2.setSpecialty(Specialty.SURGERY);

        Doctor savedDoctor = doctorRepository.save(doctor2);

        assertThat(savedDoctor).isNotNull();
    }


    @Test
    void updateDoctorLastNameTest() {
        doctor.setLastName("Minchev");
        doctorRepository.save(doctor);

        assertThat(doctor.getLastName()).isEqualTo("Minchev");
    }

    @Test
    void deleteDoctorTest() {
        doctorRepository.deleteById(doctor.getId());
        Optional<Doctor> deletedDoctor = doctorRepository.findById(doctor.getId());

        assertTrue(deletedDoctor.isEmpty());
    }
}
