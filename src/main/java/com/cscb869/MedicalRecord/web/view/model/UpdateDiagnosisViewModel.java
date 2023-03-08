package com.cscb869.MedicalRecord.web.view.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateDiagnosisViewModel {
    @NotBlank
    @Size(min = 2, max = 45, message="Min 2, Max 45")
    private String name;

    @NotBlank
    @Size(min = 2, max = 65, message="Min 2, Max 65")
    private String description;
}
