package com.cscb869.MedicalRecord.web.view.model;

import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class CreateSickSheetViewModel {
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate fromDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate toDate;

    @NotBlank
    @Size(min = 2, max = 65, message="Min 2, Max 65")
    private String description;
}
