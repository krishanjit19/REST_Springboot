package com.example.q3.service;

import com.example.q3.model.Doctor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DoctorService {

    private final Map<Long, Doctor> doctors = new HashMap<>();
    private long nextId = 1;

    public Doctor getDoctorDetails(Long id) {
        return doctors.get(id);
    }

    public Long addDoctor(Doctor doctor) {
        Long doctorId = generateDoctorId(); // Declare doctorId in this scope
        doctor.setId(doctorId);
        doctors.put(doctorId, doctor);
        return doctorId;
    }

    public void deleteDoctor(Long id) {
        doctors.remove(id);
    }

    private synchronized Long generateDoctorId() {
        return nextId++;
    }
}
