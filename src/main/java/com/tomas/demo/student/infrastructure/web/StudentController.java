package com.tomas.demo.student.infrastructure.web;

import com.tomas.demo.student.application.dto.StudentCreateRequest;
import com.tomas.demo.student.application.dto.StudentDTO;
import com.tomas.demo.student.application.service.StudentApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentApplicationService studentApplicationService;

    public StudentController(StudentApplicationService studentApplicationService) {
        this.studentApplicationService = studentApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentApplicationService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable UUID id) {
        return ResponseEntity.ok(studentApplicationService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentCreateRequest request) {
        StudentDTO created = studentApplicationService.createStudent(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable UUID id,
            @Valid @RequestBody StudentCreateRequest request) {
        return ResponseEntity.ok(studentApplicationService.updateStudent(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentApplicationService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
