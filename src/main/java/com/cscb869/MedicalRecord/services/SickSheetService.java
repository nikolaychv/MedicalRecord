package com.cscb869.MedicalRecord.services;

import com.cscb869.MedicalRecord.data.entity.SickSheet;
import com.cscb869.MedicalRecord.dto.CreateSickSheetDTO;
import com.cscb869.MedicalRecord.dto.SickSheetDTO;
import com.cscb869.MedicalRecord.dto.UpdateSickSheetDTO;

import javax.validation.constraints.Min;
import java.util.List;

public interface SickSheetService {
    List<SickSheetDTO> getAllSickSheets();

    SickSheetDTO getSickSheet(@Min(1) long id);

    SickSheet createSickSheet(CreateSickSheetDTO createSickSheetDTO);

    SickSheet updateSickSheet(long id, UpdateSickSheetDTO updateSickSheetDTO);

    void deleteSickSheet(long id);
}
