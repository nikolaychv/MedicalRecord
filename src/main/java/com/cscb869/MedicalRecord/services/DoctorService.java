package com.cscb869.MedicalRecord.services;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.enums.Specialty;
import com.cscb869.MedicalRecord.dto.*;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

public interface DoctorService {
    List<DoctorDTO> getAllDoctors();

    DoctorDTO getDoctor(@Min(1) long id);

    Doctor createDoctor(CreateDoctorDTO createDoctorDTO);

    Doctor updateDoctor(long id, UpdateDoctorDTO updateDoctorDTO);

    void deleteDoctor(long id);

    List<DoctorDTO> getDoctorsByFirstNameAndLastName(String firstName, String lastName);

    List<DoctorDTO> getDoctorsByLastNameAndSpecialty(String lastName, Specialty specialty);
}
