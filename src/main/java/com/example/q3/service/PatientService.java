package com.example.q3.service;

import com.example.q3.model.Patient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PatientService {

    private final Map<Long, Patient> patients = new HashMap<>();
    private long nextId = 1;

    public Patient getPatientDetails(Long id) {
        return patients.get(id);
    }

    public Long addPatient(Patient patient) {
        Long patientId = generatePatientId();
        patient.setId(patientId);
        patients.put(patientId, patient);
        return patientId;
    }

    public void deletePatient(Long id) {
        patients.remove(id);
    }

    private synchronized Long generatePatientId() {
        return nextId++;
    }
}
