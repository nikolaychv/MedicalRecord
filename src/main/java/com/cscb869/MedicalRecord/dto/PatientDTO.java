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
public class PatientDTO {
    private long id;
    private String egn;
    private String firstName;
    private String middleName;
    private String lastName;
    private Doctor doctor;
    private boolean hasInsurance;
    private List<MedicalExamination> medicalExaminations;
}
