package com.tomas.demo.features.Enrollment;

import com.tomas.demo.errors.ResourceNotFoundException;
import com.tomas.demo.features.student.studentModel;
import com.tomas.demo.features.student.studentRepository;
import com.tomas.demo.features.subject.subjectModel;
import com.tomas.demo.features.subject.subjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private studentRepository studentRepository;

    @Autowired
    private subjectRepository subjectRepository;

    private final EnrollmentMapper enrollmentMapper = EnrollmentMapper.INSTANCE;

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public EnrollmentDTO getEnrollmentById(UUID id) {
        EnrollmentModel enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id " + id));
        return enrollmentMapper.toDto(enrollment);
    }

    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {
        studentModel student = studentRepository.findById(enrollmentDTO.studentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + enrollmentDTO.studentId()));

        subjectModel subject = subjectRepository.findById(enrollmentDTO.subjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + enrollmentDTO.subjectId()));

        EnrollmentModel enrollment = new EnrollmentModel();
        enrollment.setStudent_id(student.getId());
        enrollment.setSubject_id(subject.getId());
        enrollment.setEnrollmentDate(LocalDate.now());

        EnrollmentModel savedEnrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDto(savedEnrollment);
    }

    public void deleteEnrollment(UUID id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Enrollment not found with id " + id);
        }
        enrollmentRepository.deleteById(id);
    }
}
