package com.cscb869.MedicalRecord.web.api;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.dto.*;
import com.cscb869.MedicalRecord.services.DoctorService;
import com.cscb869.MedicalRecord.web.view.model.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@AllArgsConstructor
@Validated
public class DoctorApiController {
    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    private DoctorViewModel convertToDoctorViewModel(DoctorDTO doctorDTO) {
        return modelMapper.map(doctorDTO, DoctorViewModel.class);
    }

    @GetMapping
    public List<DoctorDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @RequestMapping("/{id}")
    public DoctorDTO getDoctor(@PathVariable("id") @Min(1) int id) {
        return doctorService.getDoctor(id);
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody CreateDoctorViewModel createDoctorViewModel) {
        return doctorService.createDoctor(modelMapper.map(createDoctorViewModel, CreateDoctorDTO.class));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Doctor updateDoctor(@PathVariable("id") long id, @RequestBody UpdateDoctorViewModel updateDoctorViewModel) {
        return doctorService.updateDoctor(id, modelMapper.map(updateDoctorViewModel, UpdateDoctorDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
    }
}
