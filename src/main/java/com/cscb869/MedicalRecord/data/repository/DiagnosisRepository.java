package com.cscb869.MedicalRecord.data.repository;

import com.cscb869.MedicalRecord.data.entity.Diagnosis;
import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    List<Diagnosis> findAllByName(String name);

    List<Diagnosis> findAllByDescription(String description);

    List<Diagnosis> findAllByNameAndDescription(String name, String description);

    List<Diagnosis> findAllByNameAndMedicalExaminations(String schoolName, MedicalExamination medicalExamination);
}
