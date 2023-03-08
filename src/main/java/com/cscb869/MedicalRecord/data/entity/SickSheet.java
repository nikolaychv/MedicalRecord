package com.cscb869.MedicalRecord.data.entity;

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
@Entity
@Table(name= "sick_sheet")
public class SickSheet extends BaseEntity {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @NotBlank
    @Size(min = 2, max = 65, message="Min 2, Max 65")
    @Column(name = "description")
    private String description;
}