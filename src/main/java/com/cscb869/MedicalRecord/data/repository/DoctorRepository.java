package com.cscb869.MedicalRecord.data.repository;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.enums.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findAllByFirstName(String firstName);

    List<Doctor> findAllByLastName(String lastName);

    List<Doctor> findAllBySpecialty(Specialty specialty);

    List<Doctor> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<Doctor> findAllByLastNameAndSpecialty(String lastName, Specialty specialty);
}
