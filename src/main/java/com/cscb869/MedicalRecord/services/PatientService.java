package com.cscb869.MedicalRecord.services;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.dto.CreatePatientDTO;
import com.cscb869.MedicalRecord.dto.PatientDTO;
import com.cscb869.MedicalRecord.dto.UpdatePatientDTO;

import javax.validation.constraints.Min;
import java.util.List;

public interface PatientService {
    List<PatientDTO> getAllPatients();

    PatientDTO getPatient(@Min(1) long id);

    Patient createPatient(CreatePatientDTO createPatientDTO);

    Patient updatePatient(long id, UpdatePatientDTO updatePatientDTO);

    void deletePatient(long id);

    List<PatientDTO> getPatientsByDoctor(Doctor doctor);
}
