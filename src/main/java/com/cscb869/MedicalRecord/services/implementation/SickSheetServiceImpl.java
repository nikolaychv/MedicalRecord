package com.cscb869.MedicalRecord.services.implementation;

import com.cscb869.MedicalRecord.data.entity.SickSheet;
import com.cscb869.MedicalRecord.data.repository.SickSheetRepository;
import com.cscb869.MedicalRecord.dto.CreateSickSheetDTO;
import com.cscb869.MedicalRecord.dto.SickSheetDTO;
import com.cscb869.MedicalRecord.dto.UpdateSickSheetDTO;
import com.cscb869.MedicalRecord.services.SickSheetService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SickSheetServiceImpl implements SickSheetService {
    private final SickSheetRepository sickSheetRepository;
    private final ModelMapper modelMapper;

    private SickSheetDTO convertToSickSheetDTO(SickSheet sickSheet) {
        return modelMapper.map(sickSheet, SickSheetDTO.class);
    }

    @Override
    public List<SickSheetDTO> getAllSickSheets() {
        return sickSheetRepository.findAll().stream()
                .map(this::convertToSickSheetDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SickSheetDTO getSickSheet(@Min(1) long id) {
        return modelMapper.map(sickSheetRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid id: " + id)), SickSheetDTO.class);
    }

    @Override
    public SickSheet createSickSheet(CreateSickSheetDTO createSickSheetDTO) {
        return sickSheetRepository.save(modelMapper.map(createSickSheetDTO, SickSheet.class));
    }

    @Override
    public SickSheet updateSickSheet(long id, UpdateSickSheetDTO updateSickSheetDTO) {
        SickSheet sickSheet = modelMapper.map(updateSickSheetDTO, SickSheet.class);
        sickSheet.setId(id);
        return sickSheetRepository.save(sickSheet);
    }

    @Override
    public void deleteSickSheet(long id) {
        sickSheetRepository.deleteById(id);
    }
}
