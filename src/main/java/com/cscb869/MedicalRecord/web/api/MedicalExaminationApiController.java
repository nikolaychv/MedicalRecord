package com.cscb869.MedicalRecord.web.api;

import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.dto.*;
import com.cscb869.MedicalRecord.services.MedicalExaminationService;
import com.cscb869.MedicalRecord.web.view.model.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/medical-examinations")
@Validated
public class MedicalExaminationApiController {
    private final MedicalExaminationService medicalExaminationService;

    private final ModelMapper modelMapper;

    private PatientViewModel convertToPatientViewModel(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, PatientViewModel.class);
    }

    @GetMapping
    public List<MedicalExaminationDTO> getAllMedicalExaminations() {
        return medicalExaminationService.getAllMedicalExaminations();
    }

    @RequestMapping("/{id}")
    public MedicalExaminationDTO getMedicalExamination(@PathVariable("id") @Min(1) int id) {
        return medicalExaminationService.getMedicalExamination(id);
    }

    @PostMapping
    public MedicalExamination createMedicalExamination(@RequestBody CreateMedicalExaminationViewModel
                                                                   createMedicalExaminationViewModel) {
        return medicalExaminationService.
                createMedicalExamination(modelMapper.map(createMedicalExaminationViewModel,
                        CreateMedicalExaminationDTO.class));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public MedicalExamination updateMedicalExamination(@PathVariable("id") long id,
                                                       @RequestBody UpdateMedicalExaminationViewModel
                                                               updateMedicalExaminationViewModel) {
        return medicalExaminationService.
                updateMedicalExamination(id, modelMapper.map(updateMedicalExaminationViewModel,
                        UpdateMedicalExaminationDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMedicalExamination(@PathVariable long id) {
        medicalExaminationService.
                deleteMedicalExamination(id);
    }
}
