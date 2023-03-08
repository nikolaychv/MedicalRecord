package com.cscb869.MedicalRecord.web.view.model;

import com.cscb869.MedicalRecord.data.entity.Diagnosis;
import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.data.entity.SickSheet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateMedicalExaminationViewModel {
    private Patient patient;

    private Doctor doctor;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate examinationDate;

    private Diagnosis diagnosis;

    @NotBlank
    @Size(min = 1, max = 50, message="Min 1, Max 50")
    private String treatment;

    private boolean needsSickSheet;

    private SickSheet sickSheet;
}
