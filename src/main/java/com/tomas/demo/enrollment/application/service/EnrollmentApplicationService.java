package com.tomas.demo.enrollment.application.service;

import com.tomas.demo.curriculum.domain.model.Career;
import com.tomas.demo.curriculum.domain.model.Subject;
import com.tomas.demo.curriculum.domain.repository.CareerRepository;
import com.tomas.demo.curriculum.domain.repository.CareerSubjectRepository;
import com.tomas.demo.curriculum.domain.repository.SubjectRepository;
import com.tomas.demo.enrollment.application.dto.EnrollmentCreateRequest;
import com.tomas.demo.enrollment.application.dto.EnrollmentDTO;
import com.tomas.demo.enrollment.application.dto.EnrollmentDetailDTO;
import com.tomas.demo.enrollment.application.mapper.EnrollmentMapper;
import com.tomas.demo.enrollment.domain.model.Enrollment;
import com.tomas.demo.enrollment.domain.model.EnrollmentStatus;
import com.tomas.demo.enrollment.domain.repository.EnrollmentRepository;
import com.tomas.demo.shared.domain.exception.BusinessValidationException;
import com.tomas.demo.shared.domain.exception.EntityNotFoundException;
import com.tomas.demo.student.domain.model.Student;
import com.tomas.demo.student.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnrollmentApplicationService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final CareerRepository careerRepository;
    private final CareerSubjectRepository careerSubjectRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentApplicationService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            SubjectRepository subjectRepository,
            CareerRepository careerRepository,
            CareerSubjectRepository careerSubjectRepository,
            EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.careerRepository = careerRepository;
        this.careerSubjectRepository = careerSubjectRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    @Transactional(readOnly = true)
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentMapper.toDtoList(enrollmentRepository.findAll());
    }

    @Transactional(readOnly = true)
    public EnrollmentDTO getEnrollmentById(UUID id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment", id));
        return enrollmentMapper.toDto(enrollment);
    }

    @Transactional(readOnly = true)
    public EnrollmentDetailDTO getEnrollmentDetail(UUID id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment", id));

        Student student = studentRepository.findById(enrollment.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student", enrollment.getStudentId()));

        Subject subject = subjectRepository.findById(enrollment.getSubjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject", enrollment.getSubjectId()));

        Career career = careerRepository.findById(enrollment.getCareerId())
                .orElseThrow(() -> new EntityNotFoundException("Career", enrollment.getCareerId()));

        return new EnrollmentDetailDTO(
                enrollment.getId(),
                student.getId(),
                student.getName(),
                student.getDni(),
                student.getFileNumber(),
                subject.getId(),
                subject.getName(),
                subject.getCode(),
                career.getId(),
                career.getName(),
                enrollment.getEnrollmentDate(),
                enrollment.getStatus(),
                enrollment.isActive()
        );
    }

    @Transactional(readOnly = true)
    public List<EnrollmentDTO> getEnrollmentsByStudent(UUID studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new EntityNotFoundException("Student", studentId);
        }
        return enrollmentMapper.toDtoList(enrollmentRepository.findByStudentId(studentId));
    }

    public EnrollmentDTO createEnrollment(EnrollmentCreateRequest request) {
        // Validate student exists
        if (!studentRepository.existsById(request.studentId())) {
            throw new EntityNotFoundException("Student", request.studentId());
        }

        // Validate career exists
        if (!careerRepository.existsById(request.careerId())) {
            throw new EntityNotFoundException("Career", request.careerId());
        }

        // Validate subject exists
        if (!subjectRepository.existsById(request.subjectId())) {
            throw new EntityNotFoundException("Subject", request.subjectId());
        }

        // Validate subject belongs to career plan
        if (!careerSubjectRepository.existsByCareerIdAndSubjectId(request.careerId(), request.subjectId())) {
            throw new BusinessValidationException("Subject does not belong to the study plan of the specified career");
        }

        // Validate active duplicate enrollment
        if (enrollmentRepository.existsByStudentIdAndSubjectIdAndCareerIdAndStatus(
                request.studentId(), request.subjectId(), request.careerId(), EnrollmentStatus.ACTIVE)) {
            throw new BusinessValidationException("Student is already actively enrolled in this subject for this career");
        }

        Enrollment enrollment = new Enrollment(
                null,
                request.studentId(),
                request.subjectId(),
                request.careerId(),
                LocalDate.now(),
                EnrollmentStatus.ACTIVE,
                true
        );

        Enrollment saved = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDto(saved);
    }

    public void cancelEnrollment(UUID id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment", id));

        enrollment.setStatus(EnrollmentStatus.DROPPED);
        enrollment.setActive(false);
        enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(UUID id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Enrollment", id);
        }
        enrollmentRepository.deleteById(id);
    }
}
