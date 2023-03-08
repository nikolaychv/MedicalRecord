package com.cscb869.MedicalRecord.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DiagnosisNotFoundException extends RuntimeException {
    public DiagnosisNotFoundException(String message) {
        super(message);
    }
}