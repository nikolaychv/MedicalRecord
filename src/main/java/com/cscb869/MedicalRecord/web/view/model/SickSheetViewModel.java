package com.cscb869.MedicalRecord.web.view.model;

import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SickSheetViewModel {
    private long id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String description;
}
