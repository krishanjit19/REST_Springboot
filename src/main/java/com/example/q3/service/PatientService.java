package com.example.q3.service;

import com.example.q3.model.Doctor;
import com.example.q3.model.Patient;
import com.example.q3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    public Patient getDoctorById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Patient updatePatient(Patient patient) {
        // Check if patient exists
        Patient existingPatient = patientRepository.findById(patient.getId()).orElse(null);
        if (existingPatient == null) {
            throw new RuntimeException("Patient not found with ID: " + patient.getId());
        }

        // Update fields (assuming you only want to update name and specialty)
        existingPatient.setName(patient.getName());
        existingPatient.setAge(patient.getAge());

        // Save the updated doctor
        return patientRepository.save(existingPatient);
    }

    // DELETE
    public void deletePaitent(Long id) {
        patientRepository.deleteById(id);
    }
    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }
}
