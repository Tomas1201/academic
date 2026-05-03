package com.tomas.demo.features.Enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable UUID id) {
        EnrollmentDTO enrollmentDTO = enrollmentService.getEnrollmentById(id);
        return ResponseEntity.ok(enrollmentDTO);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
        EnrollmentDTO createdEnrollment = enrollmentService.createEnrollment(enrollmentDTO);
        return new ResponseEntity<>(createdEnrollment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable UUID id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
