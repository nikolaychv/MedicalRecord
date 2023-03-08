package com.cscb869.MedicalRecord.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "diagnosis")
public class Diagnosis extends BaseEntity {
    @NotBlank
    @Size(min = 2, max = 45, message="Min 2, Max 45")
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min = 2, max = 65, message="Min 2, Max 65")
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "diagnosis")
    @JsonIgnoreProperties("diagnosis")
    private List<MedicalExamination> medicalExaminations;
}
