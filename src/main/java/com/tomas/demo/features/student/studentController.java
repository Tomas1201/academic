package com.tomas.demo.features.student;

import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/students")
public class studentController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getStudent(@PathVariable int id) {
        
        return ResponseEntity.status(201).body("pepe");
    }

}
