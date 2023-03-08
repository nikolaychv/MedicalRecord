package com.cscb869.MedicalRecord.services.implementations;

import com.cscb869.MedicalRecord.data.entity.Diagnosis;
import com.cscb869.MedicalRecord.data.entity.SickSheet;
import com.cscb869.MedicalRecord.data.repository.DiagnosisRepository;
import com.cscb869.MedicalRecord.data.repository.SickSheetRepository;
import com.cscb869.MedicalRecord.dto.DiagnosisDTO;
import com.cscb869.MedicalRecord.dto.SickSheetDTO;
import com.cscb869.MedicalRecord.services.implementation.DiagnosisServiceImpl;
import com.cscb869.MedicalRecord.services.implementation.SickSheetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SickSheetImplTest {
    @Spy
    private ModelMapper modelMapper;

    @Mock
    private SickSheetRepository sickSheetRepository;

    @InjectMocks
    private SickSheetServiceImpl sickSheetService;

    private SickSheet sickSheet;

    @BeforeEach
    public void init() {
        sickSheet = new SickSheet();
        sickSheet.setFromDate(LocalDate.of(2023, 3, 5));
        sickSheet.setToDate(LocalDate.of(2023, 3, 9));
        sickSheetRepository.save(sickSheet);
    }

    @Test
    void getSickSheetById() {
        // Given
        given(sickSheetRepository.findById(sickSheet.getId())).willReturn(Optional.of(sickSheet));

        // When
        SickSheetDTO sickSheetDTO = sickSheetService.getSickSheet(sickSheet.getId());

        // Then
        assertThat(sickSheetDTO.getId()).isEqualTo(sickSheet.getId());
    }
}
