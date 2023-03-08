package com.cscb869.MedicalRecord.services;

import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.dto.CreateMedicalExaminationDTO;
import com.cscb869.MedicalRecord.dto.MedicalExaminationDTO;
import com.cscb869.MedicalRecord.dto.UpdateMedicalExaminationDTO;

import javax.validation.constraints.Min;
import java.util.List;

public interface MedicalExaminationService {
    List<MedicalExaminationDTO> getAllMedicalExaminations();

    MedicalExaminationDTO getMedicalExamination(@Min(1) long id);

    MedicalExamination createMedicalExamination(CreateMedicalExaminationDTO createMedicalExaminationDTO);

    MedicalExamination updateMedicalExamination(long id, UpdateMedicalExaminationDTO updateMedicalExaminationDTO);

    void deleteMedicalExamination(long id);
}
