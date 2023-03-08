package com.cscb869.MedicalRecord.services.implementations;

import com.cscb869.MedicalRecord.data.entity.Diagnosis;
import com.cscb869.MedicalRecord.data.repository.DiagnosisRepository;
import com.cscb869.MedicalRecord.dto.DiagnosisDTO;
import com.cscb869.MedicalRecord.services.implementation.DiagnosisServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DiagnosisServiceImplTest {
    @Spy
    private ModelMapper modelMapper;

    @Mock
    private DiagnosisRepository diagnosisRepository;

    @InjectMocks
    private DiagnosisServiceImpl diagnosisService;

    private Diagnosis diagnosis;

    @BeforeEach
    public void init() {
        diagnosis = new Diagnosis();
        diagnosis.setName("Grip");
        diagnosis.setDescription("Description 9");
        diagnosisRepository.save(diagnosis);
    }

    @Test
    void getDiagnosisById() {
        // Given
        given(diagnosisRepository.findById(diagnosis.getId())).willReturn(Optional.of(diagnosis));

        // When
        DiagnosisDTO diagnosisDTO = diagnosisService.getDiagnosis(diagnosis.getId());

        // Then
        assertThat(diagnosisDTO.getId()).isEqualTo(diagnosis.getId());
    }
}
