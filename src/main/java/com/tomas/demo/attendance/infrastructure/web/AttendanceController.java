package com.tomas.demo.attendance.infrastructure.web;

import com.tomas.demo.attendance.application.dto.AttendanceCreateRequest;
import com.tomas.demo.attendance.application.dto.AttendanceDTO;
import com.tomas.demo.attendance.application.service.AttendanceApplicationService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

    private final AttendanceApplicationService attendanceApplicationService;

    public AttendanceController(AttendanceApplicationService attendanceApplicationService) {
        this.attendanceApplicationService = attendanceApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendances() {
        return ResponseEntity.ok(attendanceApplicationService.getAllAttendances());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable UUID id) {
        return ResponseEntity.ok(attendanceApplicationService.getAttendanceById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesByStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(attendanceApplicationService.getAttendancesByStudent(studentId));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesBySubject(@PathVariable UUID subjectId) {
        return ResponseEntity.ok(attendanceApplicationService.getAttendancesBySubject(subjectId));
    }

    @GetMapping("/subject/{subjectId}/date/{date}")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesBySubjectAndDate(
            @PathVariable UUID subjectId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(attendanceApplicationService.getAttendancesBySubjectAndDate(subjectId, date));
    }

    @PostMapping
    public ResponseEntity<AttendanceDTO> recordAttendance(@Valid @RequestBody AttendanceCreateRequest request) {
        AttendanceDTO created = attendanceApplicationService.recordAttendance(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable UUID id) {
        attendanceApplicationService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
