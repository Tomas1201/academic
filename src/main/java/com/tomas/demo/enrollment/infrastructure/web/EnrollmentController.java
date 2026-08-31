package com.tomas.demo.enrollment.infrastructure.web;

import com.tomas.demo.enrollment.application.dto.EnrollmentCreateRequest;
import com.tomas.demo.enrollment.application.dto.EnrollmentDTO;
import com.tomas.demo.enrollment.application.dto.EnrollmentDetailDTO;
import com.tomas.demo.enrollment.application.service.EnrollmentApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentApplicationService enrollmentApplicationService;

    public EnrollmentController(EnrollmentApplicationService enrollmentApplicationService) {
        this.enrollmentApplicationService = enrollmentApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentApplicationService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(enrollmentApplicationService.getEnrollmentById(id));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<EnrollmentDetailDTO> getEnrollmentDetail(@PathVariable UUID id) {
        return ResponseEntity.ok(enrollmentApplicationService.getEnrollmentDetail(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(enrollmentApplicationService.getEnrollmentsByStudent(studentId));
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> createEnrollment(@Valid @RequestBody EnrollmentCreateRequest request) {
        EnrollmentDTO created = enrollmentApplicationService.createEnrollment(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelEnrollment(@PathVariable UUID id) {
        enrollmentApplicationService.cancelEnrollment(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable UUID id) {
        enrollmentApplicationService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
