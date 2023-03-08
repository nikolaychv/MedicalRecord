package com.cscb869.MedicalRecord.web.view.controllers;

import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.dto.*;
import com.cscb869.MedicalRecord.exceptions.DiagnosisNotFoundException;
import com.cscb869.MedicalRecord.services.DiagnosisService;
import com.cscb869.MedicalRecord.web.view.model.*;
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
@RequestMapping("/diagnoses")
public class DiagnosisController {
    private final DiagnosisService diagnosisService;
    private final ModelMapper modelMapper;

    private DiagnosisViewModel convertToDiagnosisViewModel(DiagnosisDTO diagnosisDTO) {
        return modelMapper.map(diagnosisDTO, DiagnosisViewModel.class);
    }

    @ExceptionHandler({DiagnosisNotFoundException.class})
    public String handleException(DiagnosisNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/diagnosis-errors";
    }

    @GetMapping
    public String getDiagnoses(Model model) {
        final List<DiagnosisViewModel> diagnoses = diagnosisService.getAllDiagnoses()
                .stream().map(this::convertToDiagnosisViewModel)
                .collect(Collectors.toList());
        model.addAttribute("diagnoses", diagnoses);
        return "/diagnoses/diagnoses";
    }

    @GetMapping("/create-diagnosis")
    public String showCreateDiagnosisForm(Model model) {
        model.addAttribute("diagnosis", new CreateDiagnosisViewModel());
        return "/diagnoses/create-diagnosis";
    }

    @PostMapping("/create")
    public String createDiagnosis(@Valid @ModelAttribute("diagnosis") CreateDiagnosisViewModel diagnosis,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/diagnoses/create-diagnosis";
        }
        diagnosisService.createDiagnosis(modelMapper.map(diagnosis, CreateDiagnosisDTO.class));
        return "redirect:/diagnoses";
    }

    @GetMapping("/edit-diagnosis/{id}")
    public String showEditDiagnosisForm(Model model, @PathVariable long id) {
        model.addAttribute("diagnosis",
                modelMapper.map(diagnosisService.getDiagnosis(id), UpdateDiagnosisViewModel.class));
        return "/diagnoses/edit-diagnosis";
    }

    @PostMapping("/update/{id}")
    public String updateDiagnosis(@PathVariable long id,
                                  @Valid @ModelAttribute("patient") UpdateDiagnosisViewModel diagnosis,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/diagnoses/edit-diagnosis";
        }
        diagnosisService.updateDiagnosis(id, modelMapper.map(diagnosis, UpdateDiagnosisDTO.class));
        return "redirect:/diagnoses";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        diagnosisService.deleteDiagnosis(id);
        return "redirect:/diagnoses";
    }

    @GetMapping("/search-diagnoses")
    public String processSearchDiagnosisForm() {
        return "/diagnoses/search-diagnoses";
    }

    @GetMapping("/names-description")
    public String getDiagnosesByNameAndDescription(Model model, @RequestParam String name,
                                                   @RequestParam String description) {
        List<DiagnosisViewModel> diagnoses = diagnosisService
                .getDiagnosesByNameAndDescription(name, description)
                .stream()
                .map(this::convertToDiagnosisViewModel)
                .collect(Collectors.toList());

        model.addAttribute("diagnoses", diagnoses);
        return "/diagnoses/diagnoses";
    }

    /*
    @GetMapping("/search-diagnoses-medical-examination")
    public String processSearchDiagnosisMedicalExaminationForm() {

        return "/diagnoses/search-diagnoses-medical-examination";
    }

    @GetMapping("/names-medical-examination")
    public String getDiagnosesByNameAndMedicalExamination(Model model, @RequestParam String name,
                                                          @RequestParam MedicalExamination medicalExamination) {
        List<DiagnosisViewModel> diagnoses = diagnosisService
                .getDiagnosesByNameAndMedicalExaminations(name, medicalExamination)
                .stream()
                .map(this::convertToDiagnosisViewModel)
                .collect(Collectors.toList());
        model.addAttribute("diagnoses", diagnoses);
        return "/diagnoses/diagnoses";
    }
     */
}