package com.cscb869.MedicalRecord.data.repository;

import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.data.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MedicalExaminationRepository extends JpaRepository<MedicalExamination, Long> {
    List<MedicalExamination> findAllByPatient(Patient patient);

    List<MedicalExamination> findAllByTreatment(String treatment);
}
