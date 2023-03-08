package com.cscb869.MedicalRecord.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "medical_examination")
public class MedicalExamination extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "examination_date")
    private LocalDate examinationDate;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;

    @NotBlank
    @Size(min = 1, max = 50, message="Min 1, Max 50")
    @Column(name = "treatment")
    private String treatment;

    @Column(name = "needs_sick_sheet")
    private boolean needsSickSheet;

    @OneToOne
    @JoinColumn(name = "sick_sheet_id")
    private SickSheet sickSheet;
}