package com.cscb869.MedicalRecord.dto;

import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateDiagnosisDTO {
    private String name;
    private String description;
    private List<MedicalExamination> medicalExaminations;
}
