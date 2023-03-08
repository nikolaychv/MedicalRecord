package com.cscb869.MedicalRecord.web.api;

import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.dto.CreatePatientDTO;
import com.cscb869.MedicalRecord.dto.PatientDTO;
import com.cscb869.MedicalRecord.dto.UpdatePatientDTO;
import com.cscb869.MedicalRecord.services.PatientService;
import com.cscb869.MedicalRecord.web.view.model.CreatePatientViewModel;
import com.cscb869.MedicalRecord.web.view.model.PatientViewModel;
import com.cscb869.MedicalRecord.web.view.model.UpdatePatientViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/patients")
@Validated
public class PatientApiController {
    private final PatientService patientService;

    private final ModelMapper modelMapper;

    private PatientViewModel convertToPatientViewModel(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, PatientViewModel.class);
    }

    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @RequestMapping("/{id}")
    public PatientDTO getPatient(@PathVariable("id") @Min(1) int id) {
        return patientService.getPatient(id);
    }

    @PostMapping
    public Patient createPatient(@RequestBody CreatePatientViewModel createPatientViewModel) {
        return patientService.createPatient(modelMapper.map(createPatientViewModel, CreatePatientDTO.class));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Patient updatePatient(@PathVariable("id") long id, @RequestBody UpdatePatientViewModel updatePatientViewModel) {
        return patientService.updatePatient(id, modelMapper.map(updatePatientViewModel, UpdatePatientDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    public void deletePatient(@PathVariable long id) {
        patientService.deletePatient(id);
    }
}