package com.example.q3.controller;
import com.example.q3.model.Patient;
import com.example.q3.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public String getPatientDetails(@PathVariable Long id) {
        // Implement logic to return patient details in JSON format or default error view
        return "Patient details for ID " + id;
    }

    @PostMapping
    public String addPatient(@RequestBody Patient patient) {
        // Implement logic to add a new patient and return the patient ID
        return "Added patient with ID " + patient.getId();
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id) {
        // Implement logic to delete the patient with the specified ID
        return "Deleted patient with ID " + id;
    }
}

