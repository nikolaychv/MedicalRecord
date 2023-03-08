package com.cscb869.MedicalRecord.web.api;

import com.cscb869.MedicalRecord.data.entity.Diagnosis;
import com.cscb869.MedicalRecord.dto.*;
import com.cscb869.MedicalRecord.services.DiagnosisService;
import com.cscb869.MedicalRecord.web.view.model.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/diagnoses")
@Validated
public class DiagnosisApiController {
    private final DiagnosisService diagnosisService;

    private final ModelMapper modelMapper;

    private DiagnosisViewModel convertToDiagnosisViewModel(DiagnosisDTO diagnosisDTO) {
        return modelMapper.map(diagnosisDTO, DiagnosisViewModel.class);
    }

    @GetMapping
    public List<DiagnosisDTO> getAllDiagnosеs() {
        return diagnosisService.getAllDiagnoses();
    }

    @RequestMapping("/{id}")
    public DiagnosisDTO getDiagnosis(@PathVariable("id") @Min(1) int id) {
        return diagnosisService.getDiagnosis(id);
    }

    @PostMapping
    public Diagnosis createDiagnosis(@RequestBody CreateDiagnosisViewModel createDiagnosisViewModel) {
        return diagnosisService.createDiagnosis(modelMapper.map(createDiagnosisViewModel, CreateDiagnosisDTO.class));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Diagnosis updateDiagnosis(@PathVariable("id") long id,
                                     @RequestBody UpdateDiagnosisViewModel updateDiagnosisViewModel) {
        return diagnosisService.updateDiagnosis(id, modelMapper.map(updateDiagnosisViewModel, UpdateDiagnosisDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDiagnosis(@PathVariable long id) {
        diagnosisService.deleteDiagnosis(id);
    }
}
