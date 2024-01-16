package com.example.q3.controller;

import com.example.q3.model.Doctor;
import com.example.q3.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}")
    public String getDoctorDetails(@PathVariable Long id) {
        // Implement logic to return doctor details in JSON format or default error view
        return "Doctor details for ID " + id;
    }

    @PostMapping
    public String addDoctor(@RequestBody Doctor doctor) {
        // Implement logic to add a new doctor and return the doctor ID
        return "Added doctor with ID " + doctor.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        // Implement logic to delete the doctor with the specified ID
        return "Deleted doctor with ID " + id;
    }
}