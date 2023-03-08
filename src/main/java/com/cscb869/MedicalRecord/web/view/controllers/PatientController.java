package com.cscb869.MedicalRecord.web.view.controllers;

import com.cscb869.MedicalRecord.dto.CreatePatientDTO;
import com.cscb869.MedicalRecord.dto.PatientDTO;
import com.cscb869.MedicalRecord.dto.UpdatePatientDTO;
import com.cscb869.MedicalRecord.exceptions.PatientNotFoundException;
import com.cscb869.MedicalRecord.services.DoctorService;
import com.cscb869.MedicalRecord.services.PatientService;
import com.cscb869.MedicalRecord.web.view.model.CreatePatientViewModel;
import com.cscb869.MedicalRecord.web.view.model.PatientViewModel;
import com.cscb869.MedicalRecord.web.view.model.UpdatePatientViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    private final ModelMapper modelMapper;
    private final DoctorService doctorService;

    private PatientViewModel convertToPatientViewModel(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, PatientViewModel.class);
    }

    @ExceptionHandler({PatientNotFoundException.class})
    public String handleException(PatientNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/patient-errors";
    }

    @GetMapping
    public String getPatients(Model model) {
        final List<PatientViewModel> patients = patientService.getAllPatients()
                .stream().map(this::convertToPatientViewModel)
                .collect(Collectors.toList());
        model.addAttribute("patients", patients);
        return "/patients/patients";
    }

    @GetMapping("/create-patient")
    public String showCreatePatientForm(Model model) {
        model.addAttribute("patient", new CreatePatientViewModel());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "/patients/create-patient";
    }

    @PostMapping("/create")
    public String createPatient(@Valid @ModelAttribute("patient") CreatePatientViewModel patient,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("doctors", doctorService.getAllDoctors());
            return "/patients/create-patient";
        }
        patientService.createPatient(modelMapper.map(patient, CreatePatientDTO.class));
        return "redirect:/patients";
    }

    @GetMapping("/edit-patient/{id}")
    public String showEditPatientForm(Model model, @PathVariable long id) {
        model.addAttribute("patient",
                modelMapper.map(patientService.getPatient(id), UpdatePatientViewModel.class));
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "/patients/edit-patient";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable long id, @Valid @ModelAttribute("patient") UpdatePatientViewModel patient,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("doctors", doctorService.getAllDoctors());
            return "/patients/edit-patient";
        }
        patientService.updatePatient(id, modelMapper.map(patient, UpdatePatientDTO.class));
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}