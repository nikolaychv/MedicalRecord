package com.cscb869.MedicalRecord.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MedicalExaminationNotFoundException extends RuntimeException {
    public MedicalExaminationNotFoundException(String message) {
        super(message);
    }
}
