package com.tomas.demo.evaluation.infrastructure.web;

import com.tomas.demo.evaluation.application.dto.*;
import com.tomas.demo.evaluation.application.service.ExamApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/exams")
public class ExamController {

    private final ExamApplicationService examApplicationService;

    public ExamController(ExamApplicationService examApplicationService) {
        this.examApplicationService = examApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        return ResponseEntity.ok(examApplicationService.getAllExams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable UUID id) {
        return ResponseEntity.ok(examApplicationService.getExamById(id));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<ExamDetailDTO> getExamDetailById(@PathVariable UUID id) {
        return ResponseEntity.ok(examApplicationService.getExamDetailById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamDTO>> getExamsByStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(examApplicationService.getExamsByStudent(studentId));
    }

    @GetMapping("/subject/{subjectId}/career/{careerId}")
    public ResponseEntity<List<ExamDTO>> getExamsBySubjectAndCareer(
            @PathVariable UUID subjectId,
            @PathVariable UUID careerId) {
        return ResponseEntity.ok(examApplicationService.getExamsBySubjectAndCareer(subjectId, careerId));
    }

    @GetMapping("/teacher/{teacherId}/subject/{subjectId}/career/{careerId}")
    public ResponseEntity<List<ExamDTO>> getExamsByTeacherAndSubjectAndCareer(
            @PathVariable UUID teacherId,
            @PathVariable UUID subjectId,
            @PathVariable UUID careerId) {
        return ResponseEntity.ok(examApplicationService.getExamsByTeacherAndSubjectAndCareer(teacherId, subjectId, careerId));
    }

    @PostMapping("/grades")
    public ResponseEntity<ExamDTO> registerGrade(@Valid @RequestBody RegisterGradeCommand command) {
        ExamDTO created = examApplicationService.registerGrade(command);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/grades/bulk")
    public ResponseEntity<List<ExamDTO>> bulkRegisterGrades(@Valid @RequestBody BulkGradeUploadRequest request) {
        List<ExamDTO> created = examApplicationService.bulkRegisterGrades(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable UUID id) {
        examApplicationService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
