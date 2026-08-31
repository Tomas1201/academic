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
import com.tomas.demo.student.domain.model.Student;
import com.tomas.demo.student.domain.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnrollmentApplicationServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private CareerRepository careerRepository;

    @Mock
    private CareerSubjectRepository careerSubjectRepository;

    @Spy
    private EnrollmentMapper enrollmentMapper = Mappers.getMapper(EnrollmentMapper.class);

    @InjectMocks
    private EnrollmentApplicationService enrollmentApplicationService;

    private UUID studentId;
    private UUID subjectId;
    private UUID careerId;
    private UUID enrollmentId;

    @BeforeEach
    void setUp() {
        studentId = UUID.randomUUID();
        subjectId = UUID.randomUUID();
        careerId = UUID.randomUUID();
        enrollmentId = UUID.randomUUID();
    }

    @Test
    void createEnrollment_Success() {
        EnrollmentCreateRequest request = new EnrollmentCreateRequest(studentId, subjectId, careerId);

        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(careerRepository.existsById(careerId)).thenReturn(true);
        when(subjectRepository.existsById(subjectId)).thenReturn(true);
        when(careerSubjectRepository.existsByCareerIdAndSubjectId(careerId, subjectId)).thenReturn(true);
        when(enrollmentRepository.existsByStudentIdAndSubjectIdAndCareerIdAndStatus(
                studentId, subjectId, careerId, EnrollmentStatus.ACTIVE)).thenReturn(false);

        Enrollment savedEnrollment = new Enrollment(
                enrollmentId,
                studentId,
                subjectId,
                careerId,
                LocalDate.now(),
                EnrollmentStatus.ACTIVE,
                true
        );
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(savedEnrollment);

        EnrollmentDTO result = enrollmentApplicationService.createEnrollment(request);

        assertNotNull(result);
        assertEquals(enrollmentId, result.id());
        assertEquals(studentId, result.studentId());
        assertEquals(subjectId, result.subjectId());
        assertEquals(careerId, result.careerId());
        assertEquals(EnrollmentStatus.ACTIVE, result.status());
    }

    @Test
    void createEnrollment_SubjectNotInCareerPlan_ThrowsException() {
        EnrollmentCreateRequest request = new EnrollmentCreateRequest(studentId, subjectId, careerId);

        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(careerRepository.existsById(careerId)).thenReturn(true);
        when(subjectRepository.existsById(subjectId)).thenReturn(true);
        when(careerSubjectRepository.existsByCareerIdAndSubjectId(careerId, subjectId)).thenReturn(false);

        assertThrows(BusinessValidationException.class, () -> enrollmentApplicationService.createEnrollment(request));
        verify(enrollmentRepository, never()).save(any());
    }

    @Test
    void getEnrollmentDetail_Success() {
        Enrollment enrollment = new Enrollment(
                enrollmentId,
                studentId,
                subjectId,
                careerId,
                LocalDate.now(),
                EnrollmentStatus.ACTIVE,
                true
        );

        Student student = new Student(studentId, "Ada Lovelace", "ada@uni.edu", "33333333", "pass", 1002, true);
        Subject subject = new Subject(subjectId, "Math I", "MATH101", "Calculus", 8, 1, true);
        Career career = new Career(careerId, "Software Engineering", "SE", "SE Program", true);

        when(enrollmentRepository.findById(enrollmentId)).thenReturn(Optional.of(enrollment));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));
        when(careerRepository.findById(careerId)).thenReturn(Optional.of(career));

        EnrollmentDetailDTO detail = enrollmentApplicationService.getEnrollmentDetail(enrollmentId);

        assertNotNull(detail);
        assertEquals(enrollmentId, detail.id());
        assertEquals("Ada Lovelace", detail.studentName());
        assertEquals("Math I", detail.subjectName());
        assertEquals("Software Engineering", detail.careerName());
    }
}
