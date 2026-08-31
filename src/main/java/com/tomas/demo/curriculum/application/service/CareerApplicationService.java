package com.tomas.demo.curriculum.application.service;

import com.tomas.demo.curriculum.application.dto.AddSubjectToCareerRequest;
import com.tomas.demo.curriculum.application.dto.CareerDTO;
import com.tomas.demo.curriculum.application.dto.CareerSubjectDTO;
import com.tomas.demo.curriculum.application.mapper.CareerMapper;
import com.tomas.demo.curriculum.domain.model.Career;
import com.tomas.demo.curriculum.domain.model.CareerSubject;
import com.tomas.demo.curriculum.domain.model.Subject;
import com.tomas.demo.curriculum.domain.repository.CareerRepository;
import com.tomas.demo.curriculum.domain.repository.CareerSubjectRepository;
import com.tomas.demo.curriculum.domain.repository.SubjectRepository;
import com.tomas.demo.shared.domain.exception.BusinessValidationException;
import com.tomas.demo.shared.domain.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CareerApplicationService {

    private final CareerRepository careerRepository;
    private final SubjectRepository subjectRepository;
    private final CareerSubjectRepository careerSubjectRepository;
    private final CareerMapper careerMapper;

    public CareerApplicationService(
            CareerRepository careerRepository,
            SubjectRepository subjectRepository,
            CareerSubjectRepository careerSubjectRepository,
            CareerMapper careerMapper) {
        this.careerRepository = careerRepository;
        this.subjectRepository = subjectRepository;
        this.careerSubjectRepository = careerSubjectRepository;
        this.careerMapper = careerMapper;
    }

    @Transactional(readOnly = true)
    public List<CareerDTO> getAllCareers() {
        return careerMapper.toDtoList(careerRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CareerDTO getCareerById(UUID id) {
        Career career = careerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Career", id));
        return careerMapper.toDto(career);
    }

    public CareerDTO createCareer(CareerDTO dto) {
        Career career = careerMapper.toModel(dto);
        Career saved = careerRepository.save(career);
        return careerMapper.toDto(saved);
    }

    public CareerDTO updateCareer(UUID id, CareerDTO dto) {
        Career career = careerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Career", id));

        career.setName(dto.name());
        career.setCode(dto.code());
        career.setDescription(dto.description());
        career.setActive(dto.active());

        Career updated = careerRepository.save(career);
        return careerMapper.toDto(updated);
    }

    public void deleteCareer(UUID id) {
        if (!careerRepository.existsById(id)) {
            throw new EntityNotFoundException("Career", id);
        }
        careerRepository.deleteById(id);
    }

    public CareerSubjectDTO addSubjectToCareerPlan(UUID careerId, AddSubjectToCareerRequest request) {
        Career career = careerRepository.findById(careerId)
                .orElseThrow(() -> new EntityNotFoundException("Career", careerId));

        Subject subject = subjectRepository.findById(request.subjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject", request.subjectId()));

        if (careerSubjectRepository.existsByCareerIdAndSubjectId(careerId, request.subjectId())) {
            throw new BusinessValidationException("Subject is already part of this career plan");
        }

        CareerSubject careerSubject = new CareerSubject(
                career,
                subject,
                request.semester(),
                request.studyYear(),
                request.mandatory()
        );

        CareerSubject saved = careerSubjectRepository.save(careerSubject);

        return new CareerSubjectDTO(
                saved.getId(),
                career.getId(),
                career.getName(),
                subject.getId(),
                subject.getName(),
                subject.getCode(),
                saved.getSemester(),
                saved.getStudyYear(),
                saved.isMandatory()
        );
    }

    @Transactional(readOnly = true)
    public List<CareerSubjectDTO> getCareerPlan(UUID careerId) {
        if (!careerRepository.existsById(careerId)) {
            throw new EntityNotFoundException("Career", careerId);
        }

        return careerSubjectRepository.findByCareerId(careerId).stream()
                .map(cs -> new CareerSubjectDTO(
                        cs.getId(),
                        cs.getCareer().getId(),
                        cs.getCareer().getName(),
                        cs.getSubject().getId(),
                        cs.getSubject().getName(),
                        cs.getSubject().getCode(),
                        cs.getSemester(),
                        cs.getStudyYear(),
                        cs.isMandatory()
                ))
                .collect(Collectors.toList());
    }
}
