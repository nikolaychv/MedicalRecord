package com.cscb869.MedicalRecord.data.entity;

import com.cscb869.MedicalRecord.data.enums.Specialty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "doctor")
public class Doctor extends BaseEntity {
    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    @Column(name = "middle_name")
    private String middleName;

    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    @Column(name = "last_name")
    private String lastName;

    @Enumerated
    @Column(name = "specialty")
    private Specialty specialty;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnoreProperties("doctor")
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnoreProperties("doctor")
    private List<MedicalExamination> medicalExaminations;
}
