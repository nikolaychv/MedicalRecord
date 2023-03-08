package com.cscb869.MedicalRecord.dto;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreatePatientDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String egn;
    private Doctor doctor;
    private boolean hasInsurance;
    private List<MedicalExamination> medicalExaminations;
}
