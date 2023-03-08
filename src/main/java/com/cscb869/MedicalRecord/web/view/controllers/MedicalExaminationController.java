package com.cscb869.MedicalRecord.web.view.controllers;

import com.cscb869.MedicalRecord.dto.*;
import com.cscb869.MedicalRecord.exceptions.MedicalExaminationNotFoundException;
import com.cscb869.MedicalRecord.services.*;
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
@RequestMapping("/medical-examinations")
public class MedicalExaminationController {
    private final MedicalExaminationService medicalExaminationService;
    private final ModelMapper modelMapper;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final DiagnosisService diagnosisService;
    private final SickSheetService sickSheetService;

    private MedicalExaminationViewModel convertToMedicalExaminationViewModel(MedicalExaminationDTO
                                                                                     medicalExaminationDTO) {
        return modelMapper.map(medicalExaminationDTO, MedicalExaminationViewModel.class);
    }

    @ExceptionHandler({MedicalExaminationNotFoundException.class})
    public String handleException(MedicalExaminationNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/medical-examination-errors";
    }

    @GetMapping
    public String getMedicalExaminations(Model model) {
        final List<MedicalExaminationViewModel> medicalExaminations =
                medicalExaminationService.getAllMedicalExaminations()
                .stream().map(this::convertToMedicalExaminationViewModel)
                .collect(Collectors.toList());
        model.addAttribute("medicalExaminations", medicalExaminations);
        return "/medical-examinations/medical-examinations";
    }

    @GetMapping("/create-medical-examination")
    public String showCreateMedicalExaminationForm(Model model) {
        model.addAttribute("medicalExamination", new CreateMedicalExaminationViewModel());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("diagnoses", diagnosisService.getAllDiagnoses());
        model.addAttribute("sickSheets", sickSheetService.getAllSickSheets());
        return "/medical-examinations/create-medical-examination";
    }

    @PostMapping("/create")
    public String createMedicalExamination(@Valid @ModelAttribute("medicalExamination")
                                               CreateMedicalExaminationViewModel medicalExamination,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patients", patientService.getAllPatients());
            model.addAttribute("doctors", doctorService.getAllDoctors());
            model.addAttribute("diagnoses", diagnosisService.getAllDiagnoses());
            model.addAttribute("sickSheets", sickSheetService.getAllSickSheets());
            return "/medical-examinations/create-medical-examination";
        }
        medicalExaminationService.createMedicalExamination(modelMapper.map(medicalExamination,
                CreateMedicalExaminationDTO.class));
        return "redirect:/medical-examinations";
    }

    @GetMapping("/edit-medical-examination/{id}")
    public String showEditMedicalExaminationForm(Model model, @PathVariable long id) {
        model.addAttribute("medicalExamination",
                modelMapper.map(medicalExaminationService.getMedicalExamination(id),
                        UpdateMedicalExaminationViewModel.class));
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("diagnoses", diagnosisService.getAllDiagnoses());
        model.addAttribute("sickSheets", sickSheetService.getAllSickSheets());
        return "/medical-examinations/edit-medical-examination";
    }

    @PostMapping("/update/{id}")
    public String updateMedicalExamination(@PathVariable long id,
                                           @Valid @ModelAttribute("medicalExamination")
                                           UpdateMedicalExaminationViewModel medicalExamination,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patients", patientService.getAllPatients());
            model.addAttribute("doctors", doctorService.getAllDoctors());
            model.addAttribute("diagnoses", diagnosisService.getAllDiagnoses());
            model.addAttribute("sickSheets", sickSheetService.getAllSickSheets());
            return "/medical-examinations/edit-medical-examination";
        }
        medicalExaminationService.updateMedicalExamination(id,
                modelMapper.map(medicalExamination, UpdateMedicalExaminationDTO.class));
        return "redirect:/medical-examinations";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        medicalExaminationService.deleteMedicalExamination(id);
        return "redirect:/medical-examinations";
    }
}