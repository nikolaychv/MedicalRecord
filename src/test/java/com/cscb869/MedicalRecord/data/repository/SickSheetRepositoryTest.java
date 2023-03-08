package com.cscb869.MedicalRecord.data.repository;

import com.cscb869.MedicalRecord.data.entity.SickSheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class SickSheetRepositoryTest {
    @Autowired
    private SickSheetRepository sickSheetRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private SickSheet sickSheet;

    @BeforeEach
    public void setup() {
        sickSheet = new SickSheet();
        sickSheet.setFromDate(LocalDate.of(2024, 3, 5));
        sickSheet.setToDate(LocalDate.of(2024, 3, 9));
        sickSheet.setDescription("Description 9");

        testEntityManager.persistAndFlush(sickSheet);
    }

    @Test
    void getSickSheetByIdTest() {
        Optional<SickSheet> sickSheetById = sickSheetRepository.findById(sickSheet.getId());
        assertTrue(sickSheetById.isPresent());
    }

    @Test
    void findAllByFromDateTest() {
        LocalDate fromDate = LocalDate.of(2024, 3, 7);

        SickSheet sickSheet2 = new SickSheet();
        sickSheet2.setFromDate(fromDate);
        sickSheet2.setToDate(LocalDate.of(2024, 3, 8));
        sickSheet2.setDescription("Description 3");
        testEntityManager.persistAndFlush(sickSheet2);

        SickSheet sickSheet3 = new SickSheet();

        sickSheet3.setFromDate(fromDate);
        sickSheet3.setToDate(LocalDate.of(2024, 3, 13));
        sickSheet3.setDescription("Description 5");
        testEntityManager.persistAndFlush(sickSheet3);

        List<SickSheet> sickSheetList = Arrays.asList(sickSheet2, sickSheet3);

        assertIterableEquals(sickSheetList, sickSheetRepository.findAllByFromDate(fromDate));
    }

    @Test
    void saveSickSheetTest() {
        SickSheet sickSheet2 = new SickSheet();
        sickSheet2.setFromDate(LocalDate.of(2024, 3, 5));
        sickSheet2.setToDate(LocalDate.of(2024, 3, 9));
        sickSheet2.setDescription("Description 9");

        SickSheet savedSickSheet = sickSheetRepository.save(sickSheet2);

        assertThat(savedSickSheet).isNotNull();
    }


    @Test
    void updateSickSheetFromDateTest() {
        sickSheet.setFromDate(LocalDate.of(2024, 5, 6));
        sickSheetRepository.save(sickSheet);

        assertThat(sickSheet.getFromDate()).isEqualTo(LocalDate.of(2024, 5, 6));
    }

    @Test
    void deleteSickSheetTest() {
        sickSheetRepository.deleteById(sickSheet.getId());
        Optional<SickSheet> deletedSickSheet = sickSheetRepository.findById(sickSheet.getId());

        assertTrue(deletedSickSheet.isEmpty());
    }
}