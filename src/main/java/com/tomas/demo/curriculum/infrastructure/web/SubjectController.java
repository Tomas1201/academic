package com.tomas.demo.curriculum.infrastructure.web;

import com.tomas.demo.curriculum.application.dto.SubjectDTO;
import com.tomas.demo.curriculum.application.service.SubjectApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/curriculum/subjects")
public class SubjectController {

    private final SubjectApplicationService subjectApplicationService;

    public SubjectController(SubjectApplicationService subjectApplicationService) {
        this.subjectApplicationService = subjectApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        return ResponseEntity.ok(subjectApplicationService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable UUID id) {
        return ResponseEntity.ok(subjectApplicationService.getSubjectById(id));
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        SubjectDTO created = subjectApplicationService.createSubject(subjectDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable UUID id, @Valid @RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.ok(subjectApplicationService.updateSubject(id, subjectDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable UUID id) {
        subjectApplicationService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
}
