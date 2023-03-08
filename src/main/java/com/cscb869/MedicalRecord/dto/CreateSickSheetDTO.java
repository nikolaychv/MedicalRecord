package com.cscb869.MedicalRecord.dto;

import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateSickSheetDTO {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String description;
}
