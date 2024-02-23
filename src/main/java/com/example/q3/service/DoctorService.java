package com.example.q3.service;

import com.example.q3.model.Doctor;
import com.example.q3.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // CREATE
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor); // Saves the doctor and returns the persisted object
    }

    // READ
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Doctor updateDoctor(Doctor doctor) {
        // Check if doctor exists
        Doctor existingDoctor = doctorRepository.findById(doctor.getId()).orElse(null);
        if (existingDoctor == null) {
            throw new RuntimeException("Doctor not found with ID: " + doctor.getId());
        }

        // Update fields (assuming you only want to update name and specialty)
        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialty(doctor.getSpecialty());

        // Save the updated doctor
        return doctorRepository.save(existingDoctor);
    }

    // DELETE
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
