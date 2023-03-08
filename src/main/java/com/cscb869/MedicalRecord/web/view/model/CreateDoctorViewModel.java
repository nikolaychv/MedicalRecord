package com.cscb869.MedicalRecord.web.view.model;

import com.cscb869.MedicalRecord.data.enums.Specialty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateDoctorViewModel {
    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    private String middleName;

    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    private String lastName;

    @Enumerated
    private Specialty specialty;
}
