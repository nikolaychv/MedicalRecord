package com.cscb869.MedicalRecord.web.view.model;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreatePatientViewModel {
    @NotBlank
    @Size(min = 10, max = 10)
    private String egn;

    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    @Pattern(regexp = "^([A-Z]).*", message = "First name has to start with a capital letter!")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    @Pattern(regexp = "^([A-Z]).*", message = "Middle name has to start with a capital letter!")
    private String middleName;

    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    @Pattern(regexp = "^([A-Z]).*", message = "Last name has to start with a capital letter!")
    private String lastName;

    @NotNull
    private Doctor doctor;

    private boolean hasInsurance;
}
