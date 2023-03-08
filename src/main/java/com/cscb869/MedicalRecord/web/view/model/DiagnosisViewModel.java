package com.cscb869.MedicalRecord.web.view.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiagnosisViewModel {
    private long id;
    private String name;
    private String description;
}
