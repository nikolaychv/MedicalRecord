package com.cscb869.MedicalRecord.web.view.controllers;

import com.cscb869.MedicalRecord.data.enums.Specialty;
import com.cscb869.MedicalRecord.dto.*;
import com.cscb869.MedicalRecord.exceptions.DoctorNotFoundException;
import com.cscb869.MedicalRecord.services.DoctorService;
import com.cscb869.MedicalRecord.web.view.model.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    private DoctorViewModel convertToDoctorViewModel(DoctorDTO doctorDTO) {
        return modelMapper.map(doctorDTO, DoctorViewModel.class);
    }

    @ExceptionHandler({DoctorNotFoundException.class})
    public String handleException(DoctorNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/doctor-errors";
    }

    @GetMapping
    public String getDoctors(Model model) {
        final List<DoctorViewModel> doctors = doctorService.getAllDoctors()
                .stream().map(this::convertToDoctorViewModel)
                .collect(Collectors.toList());
        model.addAttribute("doctors", doctors);
        return "/doctors/doctors";
    }

    @GetMapping("/create-doctor")
    public String showCreateDoctorForm(Model model) {
        model.addAttribute("doctor", new CreateDoctorViewModel());
        model.addAttribute("specialties", Specialty.values());
        return "/doctors/create-doctor";
    }

    @PostMapping("/create")
    public String createDoctor(@Valid @ModelAttribute("doctor") CreateDoctorViewModel doctor,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("specialties", Specialty.values());
            return "/doctors/create-doctor";
        }
        doctorService.createDoctor(modelMapper.map(doctor, CreateDoctorDTO.class));
        return "redirect:/doctors";
    }

    @GetMapping("/edit-doctor/{id}")
    public String showEditDoctorForm(Model model, @PathVariable long id) {
        model.addAttribute("doctor",
                modelMapper.map(doctorService.getDoctor(id), UpdateDoctorViewModel.class));
        model.addAttribute("specialties", Specialty.values());
        return "/doctors/edit-doctor";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable long id, @Valid @ModelAttribute("doctor") UpdateDoctorViewModel doctor,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("specialties", Specialty.values());
            return "/doctors/edit-doctor";
        }
        doctorService.updateDoctor(id, modelMapper.map(doctor, UpdateDoctorDTO.class));
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }

    @GetMapping("/search-doctors")
    public String processSearchDoctorForm() {
        return "/doctors/search-doctors";
    }

    @GetMapping("/firstname-lastname")
    public String getDoctorsByFirstNameAndLastName(Model model, @RequestParam String firstName,
                                                  @RequestParam String lastName) {
        List<DoctorViewModel> doctors = doctorService
                .getDoctorsByFirstNameAndLastName(firstName, lastName)
                .stream()
                .map(this::convertToDoctorViewModel)
                .collect(Collectors.toList());

        model.addAttribute("doctors", doctors);
        return "/doctors/doctors";
    }

    /*
    @GetMapping("/search-doctors-specialty")
    public String processSearchDoctorSpecialtyForm() {

        return "/doctors/search-doctors-specialty";
    }

    @GetMapping("/lastname-specialty")
    public String getDoctorsByLastNameAndSpecialty(Model model, @RequestParam String lastName,
                                                   @RequestParam Specialty specialty) {
        List<DoctorViewModel> doctors = doctorService
                .getDoctorsByLastNameAndSpecialty(lastName, specialty)
                .stream()
                .map(this::convertToDoctorViewModel)
                .collect(Collectors.toList());
        model.addAttribute("doctors", doctors);
        return "/doctors/doctors";
    }
     */
}