package com.tomas.demo.features.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exams")
public class examController {

    @Autowired
    private examService examService;

    @GetMapping
    public List<examDTO> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<examDTO> getExamById(@PathVariable UUID id) {
        return ResponseEntity.ok(examService.getExamById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<examDTO>> getExamsByStudentId(@PathVariable UUID studentId) {
        return ResponseEntity.ok(examService.getExamsByStudentId(studentId));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<examDTO>> getExamsBySubjectId(@PathVariable UUID subjectId) {
        return ResponseEntity.ok(examService.getExamsBySubjectId(subjectId));
    }

    @PostMapping
    public ResponseEntity<examDTO> createExam(@RequestBody examDTO examDto) {
        examDTO createdExam = examService.createExam(examDto);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<examDTO> updateExam(@PathVariable UUID id, @RequestBody examDTO examDto) {
        examDTO updatedExam = examService.updateExam(id, examDto);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable UUID id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
