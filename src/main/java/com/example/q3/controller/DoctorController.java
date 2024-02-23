package com.example.q3.controller;

import com.example.q3.model.Doctor;
import com.example.q3.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctorList";
    }

    @GetMapping("/doctors/{id}")
    public String getDoctorProfile(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return "error";
        }
        model.addAttribute("doctor", doctor);
        return "doctorProfile";
    }

    // CREATE
    @PostMapping("/doctors/new")
    public String createDoctor(@ModelAttribute Doctor doctor, Model model) {
        try {
            Doctor newDoctor = doctorService.createDoctor(doctor);
            model.addAttribute("successMessage", "Doctor created successfully with ID: " + newDoctor.getId());
            return "redirect:/doctors"; // Redirect to doctor list after successful creation
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error creating doctor: " + e.getMessage());
            return "doctorForm"; // Return to form with error message
        }
    }

    @GetMapping("/doctors/new")
    public String createDoctorView() {
        return "doctorForm";
    }

    // UPDATE
    @GetMapping("/doctors/{id}/edit")
    public String editDoctor(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return "error";
        }
        model.addAttribute("id", id);
        return "editDoctorForm"; // Return the editDoctorForm template
    }
    @PostMapping("/doctors/{id}/update")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute Doctor doctor, Model model) {
        try {
            doctorService.updateDoctor(doctor);
            model.addAttribute("successMessage", "Doctor updated successfully with ID: " + doctor.getId());
            return "redirect:/doctors"; // Redirect to doctor list after successful update
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating doctor: " + e.getMessage());
            return "editDoctorForm"; // Return to edit form with error message
        }
    }
    @PostMapping("/doctors/{id}/delete")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors"; // Redirect to doctor list after successful deletion
    }
}
