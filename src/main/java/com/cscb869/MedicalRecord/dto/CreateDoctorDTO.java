package com.cscb869.MedicalRecord.dto;

import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.data.enums.Specialty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateDoctorDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private Specialty specialty;
    private List<Patient> patients;
    private List<MedicalExamination> medicalExaminations;
}
