package com.cscb869.MedicalRecord.services.implementation;

import com.cscb869.MedicalRecord.data.entity.Doctor;
import com.cscb869.MedicalRecord.data.entity.Patient;
import com.cscb869.MedicalRecord.data.enums.Specialty;
import com.cscb869.MedicalRecord.data.repository.DoctorRepository;
import com.cscb869.MedicalRecord.dto.CreateDoctorDTO;
import com.cscb869.MedicalRecord.dto.DoctorDTO;
import com.cscb869.MedicalRecord.dto.PatientDTO;
import com.cscb869.MedicalRecord.dto.UpdateDoctorDTO;
import com.cscb869.MedicalRecord.services.DoctorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    private DoctorDTO convertToDoctorDTO(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToDoctorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDTO getDoctor(@Min(1) long id) {
        return modelMapper.map(doctorRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid id: " + id)), DoctorDTO.class);
    }

    @Override
    public Doctor createDoctor(CreateDoctorDTO createDoctorDTO) {
        return doctorRepository.save(modelMapper.map(createDoctorDTO, Doctor.class));
    }

    @Override
    public Doctor updateDoctor(long id, UpdateDoctorDTO updateDoctorDTO) {
        Doctor doctor = modelMapper.map(updateDoctorDTO, Doctor.class);
        doctor.setId(id);
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public List<DoctorDTO> getDoctorsByFirstNameAndLastName(String firstName, String lastName) {
        return doctorRepository.findAllByFirstNameAndLastName(firstName, lastName).stream()
                .map(this::convertToDoctorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDTO> getDoctorsByLastNameAndSpecialty(String lastName, Specialty specialty) {
        return doctorRepository.findAllByLastNameAndSpecialty(lastName, specialty).stream()
                .map(this::convertToDoctorDTO)
                .collect(Collectors.toList());
    }
}
