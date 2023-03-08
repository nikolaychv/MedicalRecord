package com.cscb869.MedicalRecord.services.implementation;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.entity.MedicalExamination;
import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.data.repository.PatientRepository;
import com.cscb869.MedicalRecord.dto.CreatePatientDTO;
import com.cscb869.MedicalRecord.dto.PatientDTO;
import com.cscb869.MedicalRecord.dto.UpdatePatientDTO;
import com.cscb869.MedicalRecord.services.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    private PatientDTO convertToPatientDTO(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToPatientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatient(@Min(1) long id) {
        return modelMapper.map(patientRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid id: " + id)), PatientDTO.class);
    }

    @Override
    public Patient createPatient(CreatePatientDTO createPatientDTO) {
        return patientRepository.save(modelMapper.map(createPatientDTO, Patient.class));
    }

    @Override
    public Patient updatePatient(long id, UpdatePatientDTO updatePatientDTO) {
        Patient patient = modelMapper.map(updatePatientDTO, Patient.class);
        patient.setId(id);
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<PatientDTO> getPatientsByDoctor(Doctor doctor) {
        return patientRepository.findAllByDoctor(doctor).stream()
                .map(this::convertToPatientDTO)
                .collect(Collectors.toList());
    }
}
