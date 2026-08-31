package com.tomas.demo.faculty.infrastructure.web;

import com.tomas.demo.faculty.application.dto.TeacherCreateRequest;
import com.tomas.demo.faculty.application.dto.TeacherDTO;
import com.tomas.demo.faculty.application.service.TeacherApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/faculty/teachers")
public class TeacherController {

    private final TeacherApplicationService teacherApplicationService;

    public TeacherController(TeacherApplicationService teacherApplicationService) {
        this.teacherApplicationService = teacherApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherApplicationService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable UUID id) {
        return ResponseEntity.ok(teacherApplicationService.getTeacherById(id));
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@Valid @RequestBody TeacherCreateRequest request) {
        TeacherDTO created = teacherApplicationService.createTeacher(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(
            @PathVariable UUID id,
            @Valid @RequestBody TeacherCreateRequest request) {
        return ResponseEntity.ok(teacherApplicationService.updateTeacher(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable UUID id) {
        teacherApplicationService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
