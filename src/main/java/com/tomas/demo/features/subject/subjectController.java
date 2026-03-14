package com.tomas.demo.features.subject;

import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/subjects")
public class subjectController {

    @Autowired
    private subjectService subjectService;

    @GetMapping("/{id}")
    public ResponseEntity<subjectDTO> getSubject(@PathVariable int id) {
        return ResponseEntity.status(201).body(subjectService.getSubject(id));
    }

    public ResponseEntity<subjectDTO> createSubject(@RequestBody subjectModel subject) {
        return ResponseEntity.status(201).body(subjectService.createSubject(subject));
    }

    public ResponseEntity<subjectDTO> updateSubject(@RequestBody subjectModel subject) {
        return ResponseEntity.status(201).body(subjectService.updateSubject(subject));
    }

    public ResponseEntity<subjectDTO> deleteSubject(@PathVariable int id) {
        return ResponseEntity.status(201).body(subjectService.deleteSubject(id));
    }
}
