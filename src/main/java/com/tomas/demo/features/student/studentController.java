package com.tomas.demo.features.student;

import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/students")
public class studentController {

    @Autowired
    private studentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<studentDTO> getStudent(@PathVariable int id) {
        
        return ResponseEntity.status(201).body(studentService.getStudent(id));
    }

    public ResponseEntity<studentDTO> createStudent(@RequestBody studentModel student) {
        return ResponseEntity.status(201).body(studentService.createStudent(student));
    }

    public ResponseEntity<studentDTO> updateStudent(@RequestBody studentModel student) {
        return ResponseEntity.status(201).body(studentService.updateStudent(student));
    }

    public ResponseEntity<studentDTO> deleteStudent(@PathVariable int id) {
        return ResponseEntity.status(201).body(studentService.deleteStudent(id));
    }

}
