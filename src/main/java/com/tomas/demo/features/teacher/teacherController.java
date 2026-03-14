package com.tomas.demo.features.teacher;

import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/teachers")
public class teacherController {
    
    @Autowired
    private teacherService teacherService;

    @GetMapping("/{id}")
    public ResponseEntity<teacherDTO> getTeacher(@PathVariable int id) {
        return ResponseEntity.status(201).body(teacherService.getTeacher(id));
    }

    public ResponseEntity<teacherDTO> createTeacher(@RequestBody teacherModel teacher) {
        return ResponseEntity.status(201).body(teacherService.createTeacher(teacher));
    }

    public ResponseEntity<teacherDTO> updateTeacher(@RequestBody teacherModel teacher) {
        return ResponseEntity.status(201).body(teacherService.updateTeacher(teacher));
    }

    public ResponseEntity<teacherDTO> deleteTeacher(@PathVariable int id) {
        return ResponseEntity.status(201).body(teacherService.deleteTeacher(id));
    }
}
