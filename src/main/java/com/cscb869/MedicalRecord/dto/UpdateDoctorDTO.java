package com.cscb869.MedicalRecord.dto;

import com.cscb869.MedicalRecord.data.enums.Specialty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDoctorDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private Specialty specialty;
}
