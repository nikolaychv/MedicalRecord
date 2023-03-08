package com.cscb869.MedicalRecord.web.view.model;

import com.cscb869.MedicalRecord.data.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorViewModel {
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Specialty specialty;
}
