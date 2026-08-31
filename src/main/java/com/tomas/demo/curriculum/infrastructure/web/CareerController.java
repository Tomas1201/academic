package com.tomas.demo.curriculum.infrastructure.web;

import com.tomas.demo.curriculum.application.dto.AddSubjectToCareerRequest;
import com.tomas.demo.curriculum.application.dto.CareerDTO;
import com.tomas.demo.curriculum.application.dto.CareerSubjectDTO;
import com.tomas.demo.curriculum.application.service.CareerApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/curriculum/careers")
public class CareerController {

    private final CareerApplicationService careerApplicationService;

    public CareerController(CareerApplicationService careerApplicationService) {
        this.careerApplicationService = careerApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<CareerDTO>> getAllCareers() {
        return ResponseEntity.ok(careerApplicationService.getAllCareers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareerDTO> getCareerById(@PathVariable UUID id) {
        return ResponseEntity.ok(careerApplicationService.getCareerById(id));
    }

    @PostMapping
    public ResponseEntity<CareerDTO> createCareer(@Valid @RequestBody CareerDTO careerDTO) {
        CareerDTO created = careerApplicationService.createCareer(careerDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CareerDTO> updateCareer(@PathVariable UUID id, @Valid @RequestBody CareerDTO careerDTO) {
        return ResponseEntity.ok(careerApplicationService.updateCareer(id, careerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCareer(@PathVariable UUID id) {
        careerApplicationService.deleteCareer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/subjects")
    public ResponseEntity<CareerSubjectDTO> addSubjectToCareerPlan(
            @PathVariable UUID id,
            @Valid @RequestBody AddSubjectToCareerRequest request) {
        CareerSubjectDTO created = careerApplicationService.addSubjectToCareerPlan(id, request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<CareerSubjectDTO>> getCareerPlan(@PathVariable UUID id) {
        return ResponseEntity.ok(careerApplicationService.getCareerPlan(id));
    }
}
