package com.example.q3.controller;
import com.example.q3.model.Doctor;
import com.example.q3.model.Patient;
import com.example.q3.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patientList";
    }

    @GetMapping("/patients/{id}")
    public String getPatientProfile(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            return "error";
        }
        model.addAttribute("patient", patient);
        return "patientProfile";
    }

    // CREATE
    @PostMapping("/patients/new")
    public String createDoctor(@ModelAttribute Patient patient, Model model) {
        try {
            Patient newPatient = patientService.createPatient(patient);
            model.addAttribute("successMessage", "Patient created successfully with ID: " + newPatient.getId());
            return "redirect:/patients"; // Redirect to doctor list after successful creation
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error creating doctor: " + e.getMessage());
            return "patientForm"; // Return to form with error message
        }
    }

    @GetMapping("/patients/new")
    public String createDoctorView() {
        return "patientForm";
    }
    // UPDATE
    @GetMapping("/patients/{id}/edit")
    public String editPatient(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            return "error";
        }
        model.addAttribute("id", id);
        return "editPatientForm"; // Return the editDoctorForm template
    }
    @PostMapping("/patients/{id}/update")
    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient patient, Model model) {
        try {
            patientService.updatePatient(patient);
            model.addAttribute("successMessage", "Patient updated successfully with ID: " + patient.getId());
            return "redirect:/patients"; // Redirect to doctor list after successful update
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating patient: " + e.getMessage());
            return "editPatientForm"; // Return to edit form with error message
        }
    }

    @PostMapping("/patients/{id}/delete")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePaitent(id);
        return "redirect:/patients"; // Redirect to doctor list after successful deletion
    }
}
