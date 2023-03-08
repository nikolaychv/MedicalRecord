package com.cscb869.MedicalRecord.data.repository;

import com.cscb869.MedicalRecord.data.entity.Diagnosis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class DiagnosisRepositoryTest {
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Diagnosis diagnosis;

    @BeforeEach
    public void setup() {
        diagnosis = new Diagnosis();
        diagnosis.setName("Grip A");
        diagnosis.setDescription("Description 1");
        testEntityManager.persistAndFlush(diagnosis);
    }

    @Test
    void getDiagnosisByIdTest() {
        Optional<Diagnosis> diagnosisById =
                diagnosisRepository.findById(diagnosis.getId());
        assertTrue(diagnosisById.isPresent());
    }

    @Test
    void findAllByDescriptionTest() {
        String description = "Description 32";
        Diagnosis diagnosis2 = new Diagnosis();
        diagnosis2.setName("Grip A");
        diagnosis2.setDescription(description);
        testEntityManager.persistAndFlush(diagnosis2);

        Diagnosis diagnosis3 = new Diagnosis();
        diagnosis3.setName("Grip A");
        diagnosis3.setDescription(description);
        testEntityManager.persistAndFlush(diagnosis3);

        List<Diagnosis> diagnosisList = Arrays.asList(diagnosis2, diagnosis3);

        assertIterableEquals(diagnosisList, diagnosisRepository.findAllByDescription(description));
    }

    @Test
    void findAllByNameAndDescriptionTest() {
        String name = "Grip B";
        String description = "Description 15";
        Diagnosis diagnosis2 = new Diagnosis();
        diagnosis2.setName(name);
        diagnosis2.setDescription(description);
        testEntityManager.persistAndFlush(diagnosis2);

        Diagnosis diagnosis3 = new Diagnosis();
        diagnosis3.setName(name);
        diagnosis3.setDescription(description);
        testEntityManager.persistAndFlush(diagnosis3);

        List<Diagnosis> diagnosisList = Arrays.asList(diagnosis2, diagnosis3);

        assertIterableEquals(diagnosisList, diagnosisRepository.findAllByNameAndDescription(name, description));
    }

    @Test
    void findAllByEmptyNameTest() {
        assertThat(diagnosisRepository.findAllByName("").size()).isEqualTo(0);
    }

    @Test
    void findAllByNotFoundNameTest() {
        assertThat(diagnosisRepository.findAllByName("Name 3").size()).isEqualTo(0);
    }

    @Test
    void saveDiagnosisTest() {
        Diagnosis diagnosis2 = new Diagnosis();
        diagnosis2.setName("Grip A");
        diagnosis2.setDescription("Description 5");
        testEntityManager.persistAndFlush(diagnosis2);

        Diagnosis savedDiagnosis = diagnosisRepository.save(diagnosis2);

        assertThat(savedDiagnosis).isNotNull();
    }


    @Test
    void updateDiagnosisNameTest() {
        diagnosis.setName("Name 12");
        diagnosisRepository.save(diagnosis);

        assertThat(diagnosis.getName()).isEqualTo("Name 12");
    }

    @Test
    void deleteDiagnosisTest() {
        diagnosisRepository.deleteById(diagnosis.getId());
        Optional<Diagnosis> deletedDiagnosis = diagnosisRepository.findById(diagnosis.getId());

        assertTrue(deletedDiagnosis.isEmpty());
    }
}
