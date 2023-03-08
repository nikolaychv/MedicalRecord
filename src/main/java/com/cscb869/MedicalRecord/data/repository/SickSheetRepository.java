package com.cscb869.MedicalRecord.data.repository;

import com.cscb869.MedicalRecord.data.entity.SickSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface SickSheetRepository extends JpaRepository<SickSheet, Long> {
    List<SickSheet> findAllByFromDate(LocalDate fromDate);
}
