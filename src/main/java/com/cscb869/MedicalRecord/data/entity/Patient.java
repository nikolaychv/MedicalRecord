package com.cscb869.MedicalRecord.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "patient")
public class Patient extends BaseEntity {
    @NotBlank
    @Size(min = 10, max = 10)
    @Column(name = "egn", nullable = false, unique = true)
    private String egn;

    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    @Pattern(regexp = "^([A-Z]).*", message = "First name has to start with a capital letter!")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    @Pattern(regexp = "^([A-Z]).*", message = "Middle name has to start with a capital letter!")
    @Column(name = "middle_name")
    private String middleName;

    @NotBlank
    @Size(min = 2, max = 25, message="Min 2, Max 25")
    @Pattern(regexp = "^([A-Z]).*", message = "Last name has to start with a capital letter!")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "has_insurance")
    private boolean hasInsurance;

    @OneToMany(mappedBy = "patient")
    @JsonIgnoreProperties("patient")
    private List<MedicalExamination> medicalExaminations;
}
