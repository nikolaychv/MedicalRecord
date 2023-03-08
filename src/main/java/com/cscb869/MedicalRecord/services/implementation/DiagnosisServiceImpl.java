package com.cscb869.MedicalRecord.services.implementation;

import com.cscb869.MedicalRecord.data.entity.Diagnosis;
import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.data.repository.DiagnosisRepository;
import com.cscb869.MedicalRecord.dto.CreateDiagnosisDTO;
import com.cscb869.MedicalRecord.dto.DiagnosisDTO;
import com.cscb869.MedicalRecord.dto.UpdateDiagnosisDTO;
import com.cscb869.MedicalRecord.services.DiagnosisService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    private final ModelMapper modelMapper;

    private DiagnosisDTO convertToDiagnosisDTO(Diagnosis diagnosis) {
        return modelMapper.map(diagnosis, DiagnosisDTO.class);
    }

    @Override
    public List<DiagnosisDTO> getAllDiagnoses() {
        return diagnosisRepository.findAll().stream()
                .map(this::convertToDiagnosisDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DiagnosisDTO getDiagnosis(@Min(1) long id) {
        return modelMapper.map(diagnosisRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid id: " + id)), DiagnosisDTO.class);
    }

    @Override
    public Diagnosis createDiagnosis(CreateDiagnosisDTO createDiagnosisDTO) {
        return diagnosisRepository.save(modelMapper.map(createDiagnosisDTO, Diagnosis.class));
    }

    @Override
    public Diagnosis updateDiagnosis(long id, UpdateDiagnosisDTO updateDiagnosisDTO) {
        Diagnosis diagnosis = modelMapper.map(updateDiagnosisDTO, Diagnosis.class);
        diagnosis.setId(id);
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public void deleteDiagnosis(long id) {
        diagnosisRepository.deleteById(id);
    }

    @Override
    public List<DiagnosisDTO> getDiagnosesByNameAndDescription(String name, String description) {
        return diagnosisRepository.findAllByNameAndDescription(name, description).stream()
                .map(this::convertToDiagnosisDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiagnosisDTO> getDiagnosesByNameAndMedicalExaminations(String name,
                                                                      MedicalExamination medicalExamination) {
        return diagnosisRepository.findAllByNameAndMedicalExaminations(name, medicalExamination).stream()
                .map(this::convertToDiagnosisDTO)
                .collect(Collectors.toList());
    }
}
