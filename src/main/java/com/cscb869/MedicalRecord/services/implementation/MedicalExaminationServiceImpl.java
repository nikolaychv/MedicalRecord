package com.cscb869.MedicalRecord.services.implementation;

import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.data.repository.MedicalExaminationRepository;
import com.cscb869.MedicalRecord.dto.CreateMedicalExaminationDTO;
import com.cscb869.MedicalRecord.dto.MedicalExaminationDTO;
import com.cscb869.MedicalRecord.dto.UpdateMedicalExaminationDTO;
import com.cscb869.MedicalRecord.services.MedicalExaminationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicalExaminationServiceImpl implements MedicalExaminationService {
    private final MedicalExaminationRepository medicalExaminationRepository;
    private final ModelMapper modelMapper;

    private MedicalExaminationDTO convertToMedicalExaminationDTO(MedicalExamination medicalExamination) {
        return modelMapper.map(medicalExamination, MedicalExaminationDTO.class);
    }

    @Override
    public List<MedicalExaminationDTO> getAllMedicalExaminations() {
        return medicalExaminationRepository.findAll().stream()
                .map(this::convertToMedicalExaminationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalExaminationDTO getMedicalExamination(@Min(1) long id) {
        return modelMapper.map(medicalExaminationRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid id: " + id)), MedicalExaminationDTO.class);
    }

    @Override
    public MedicalExamination createMedicalExamination(CreateMedicalExaminationDTO createMedicalExaminationDTO) {
        return medicalExaminationRepository.save(modelMapper.map(createMedicalExaminationDTO, MedicalExamination.class));
    }

    @Override
    public MedicalExamination updateMedicalExamination(long id, UpdateMedicalExaminationDTO updateMedicalExaminationDTO) {
        MedicalExamination medicalExamination = modelMapper.map(updateMedicalExaminationDTO, MedicalExamination.class);
        medicalExamination.setId(id);
        return medicalExaminationRepository.save(medicalExamination);
    }

    @Override
    public void deleteMedicalExamination(long id) {
        medicalExaminationRepository.deleteById(id);
    }
}