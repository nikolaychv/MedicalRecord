package com.cscb869.MedicalRecord.web.view.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(Exception.class)
    protected String handleException(Exception exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/errors";
    }
}