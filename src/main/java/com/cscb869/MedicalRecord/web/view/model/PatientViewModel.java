package com.cscb869.MedicalRecord.web.view.model;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientViewModel {
    private long id;
    private String egn;
    private String firstName;
    private String middleName;
    private String lastName;
    private Doctor doctor;
    private boolean hasInsurance;
}
