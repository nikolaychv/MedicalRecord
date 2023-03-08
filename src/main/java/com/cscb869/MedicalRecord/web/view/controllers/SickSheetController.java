package com.cscb869.MedicalRecord.web.view.controllers;

import com.cscb869.MedicalRecord.dto.*;
import com.cscb869.MedicalRecord.exceptions.SickSheetNotFoundException;
import com.cscb869.MedicalRecord.services.MedicalExaminationService;
import com.cscb869.MedicalRecord.services.SickSheetService;
import com.cscb869.MedicalRecord.web.view.model.CreateSickSheetViewModel;
import com.cscb869.MedicalRecord.web.view.model.SickSheetViewModel;
import com.cscb869.MedicalRecord.web.view.model.UpdateSickSheetViewModel;
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
@RequestMapping("/sick-sheets")
public class SickSheetController {
    private final SickSheetService sickSheetService;
    private final ModelMapper modelMapper;

    private SickSheetViewModel convertToSickSheetViewModel(SickSheetDTO sickSheetDTO) {
        return modelMapper.map(sickSheetDTO, SickSheetViewModel.class);
    }

    @ExceptionHandler({SickSheetNotFoundException.class})
    public String handleException(SickSheetNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/sick-sheet-errors";
    }

    @GetMapping
    public String getSickSheets(Model model) {
        final List<SickSheetViewModel> sickSheets = sickSheetService.getAllSickSheets()
                .stream().map(this::convertToSickSheetViewModel)
                .collect(Collectors.toList());
        model.addAttribute("sickSheets", sickSheets);
        return "/sick-sheets/sick-sheets";
    }

    @GetMapping("/create-sick-sheet")
    public String showCreateSickSheetForm(Model model) {
        model.addAttribute("sickSheet", new CreateSickSheetViewModel());
        return "/sick-sheets/create-sick-sheet";
    }

    @PostMapping("/create")
    public String createSickSheet(@Valid @ModelAttribute("sickSheet") CreateSickSheetViewModel sickSheet,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/sick-sheets/create-sick-sheet";
        }
        sickSheetService.createSickSheet(modelMapper.map(sickSheet, CreateSickSheetDTO.class));
        return "redirect:/sick-sheets";
    }

    @GetMapping("/edit-sick-sheet/{id}")
    public String showEditSickSheetForm(Model model, @PathVariable long id) {
        model.addAttribute("sickSheet",
                modelMapper.map(sickSheetService.getSickSheet(id), UpdateSickSheetViewModel.class));
        return "/sick-sheets/edit-sick-sheet";
    }

    @PostMapping("/update/{id}")
    public String updateSickSheet(@PathVariable long id,
                                  @Valid @ModelAttribute("sickSheet") UpdateSickSheetViewModel sickSheet,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/sick-sheets/edit-sick-sheet";
        }
        sickSheetService.updateSickSheet(id, modelMapper.map(sickSheet, UpdateSickSheetDTO.class));
        return "redirect:/sick-sheets";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        sickSheetService.deleteSickSheet(id);
        return "redirect:/sick-sheets";
    }
}