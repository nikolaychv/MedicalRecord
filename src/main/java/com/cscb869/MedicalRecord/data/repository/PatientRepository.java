package com.cscb869.MedicalRecord.data.repository;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.data.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<Patient> findAllByFirstName(String firstName);

    List<Patient> findAllByLastName(String lastName);

    List<Patient> findAllByDoctor(Doctor doctor);
}
