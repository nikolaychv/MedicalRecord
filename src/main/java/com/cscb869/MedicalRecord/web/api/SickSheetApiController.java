package com.cscb869.MedicalRecord.web.api;

import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.data.entity.SickSheet;
import com.cscb869.MedicalRecord.dto.*;
import com.cscb869.MedicalRecord.services.PatientService;
import com.cscb869.MedicalRecord.services.SickSheetService;
import com.cscb869.MedicalRecord.web.view.model.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/sick-sheets")
@Validated
public class SickSheetApiController {
    private final SickSheetService sickSheetService;

    private final ModelMapper modelMapper;

    private PatientViewModel convertToPatientViewModel(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, PatientViewModel.class);
    }

    @GetMapping
    public List<SickSheetDTO> getAllSickSheets() {
        return sickSheetService.getAllSickSheets();
    }

    @RequestMapping("/{id}")
    public SickSheetDTO getSickSheet(@PathVariable("id") @Min(1) int id) {
        return sickSheetService.getSickSheet(id);
    }

    @PostMapping
    public SickSheet createSickSheet(@RequestBody CreateSickSheetViewModel createSickSheetViewModel) {
        return sickSheetService.createSickSheet(modelMapper.map(createSickSheetViewModel, CreateSickSheetDTO.class));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public SickSheet updateSickSheet(@PathVariable("id") long id,
                                   @RequestBody UpdateSickSheetViewModel updateSickSheetViewModel) {
        return sickSheetService.updateSickSheet(id, modelMapper.map(updateSickSheetViewModel,
                UpdateSickSheetDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSickSheet(@PathVariable long id) {
        sickSheetService.deleteSickSheet(id);
    }
}
