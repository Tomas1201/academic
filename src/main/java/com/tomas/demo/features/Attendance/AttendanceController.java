package com.tomas.demo.features.Attendance;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assistance")
public class AttendanceController {
    
    @Autowired
    private AttendanceService AttendanceServiceI;

    /*
    @GetMapping("/")
    public ResponseEntity<List<AttendanceListDTO>> getAssistancesListForSubject(@PathVariable int subjectId) {
        return ResponseEntity.status(201).body(AttendanceService.getAssistancesListForSubject(subjectId));
    }
    */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<AttendanceModel>> getAssistance(@PathVariable int id) {
        return ResponseEntity.status(201).body(AttendanceServiceI.getAttendance(id));
    }

    public ResponseEntity<AttendanceModel> createAssistance(@RequestBody AttendanceModel assistance) {
        return ResponseEntity.status(201).body(AttendanceServiceI.createAttendance(assistance));
    }

    public ResponseEntity<AttendanceModel> updateAssistance(@RequestBody AttendanceModel assistance) {
        return ResponseEntity.status(201).body(AttendanceServiceI.updateAttendance(assistance));
    }

    public ResponseEntity<AttendanceModel> deleteAssistance(@PathVariable int id) {
        return ResponseEntity.status(201).body(AttendanceServiceI.deleteAttendance(id));
    }
}
