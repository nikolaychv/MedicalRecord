package com.cscb869.MedicalRecord.services;

import com.cscb869.MedicalRecord.data.entity.Diagnosis;
import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.dto.*;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

public interface DiagnosisService {
    List<DiagnosisDTO> getAllDiagnoses();

    DiagnosisDTO getDiagnosis(@Min(1) long id);

    Diagnosis createDiagnosis(CreateDiagnosisDTO createDiagnosisDTO);

    Diagnosis updateDiagnosis(long id, UpdateDiagnosisDTO updateDiagnosisDTO);

    void deleteDiagnosis(long id);

    List<DiagnosisDTO> getDiagnosesByNameAndDescription(String name, String description);

    List<DiagnosisDTO> getDiagnosesByNameAndMedicalExaminations(String schoolName,
                                                               MedicalExamination medicalExamination);
}
