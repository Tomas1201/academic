package com.tomas.demo.features.career;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/careers")
public class careerController {

    @Autowired
    private careerService careerService;

    @GetMapping("/{id}")
    public ResponseEntity<careerDTO> getCareer(@PathVariable int id) {
        return ResponseEntity.status(201).body(careerService.getCareer(id));
    }

    public ResponseEntity<careerDTO> createCareer(@Valid @RequestBody careerDTO career) {
        return ResponseEntity.status(201).body(careerService.createCareer(career));
    }

    public ResponseEntity<careerDTO> updateCareer(@Valid @RequestBody careerDTO career) {
        return ResponseEntity.status(201).body(careerService.updateCareer(career));
    }

    public ResponseEntity<careerDTO> deleteCareer(@PathVariable int id) {
        return ResponseEntity.status(201).body(careerService.deleteCareer(id));
    }
}
