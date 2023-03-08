package com.cscb869.MedicalRecord.dto;

import com.cscb869.MedicalRecord.data.entity.Diagnosis;
import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.data.entity.SickSheet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateMedicalExaminationDTO {
    private Patient patient;
    private Doctor doctor;
    private LocalDate examinationDate;
    private Diagnosis diagnosis;
    private String treatment;
    private boolean needsSickSheet;
    private SickSheet sickSheet;
}
