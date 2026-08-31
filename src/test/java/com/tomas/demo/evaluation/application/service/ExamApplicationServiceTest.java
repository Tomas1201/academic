package com.tomas.demo.evaluation.application.service;

import com.tomas.demo.curriculum.domain.model.Career;
import com.tomas.demo.curriculum.domain.model.Subject;
import com.tomas.demo.curriculum.domain.repository.CareerRepository;
import com.tomas.demo.curriculum.domain.repository.SubjectRepository;
import com.tomas.demo.enrollment.domain.model.EnrollmentStatus;
import com.tomas.demo.enrollment.domain.repository.EnrollmentRepository;
import com.tomas.demo.evaluation.application.dto.*;
import com.tomas.demo.evaluation.application.mapper.ExamMapper;
import com.tomas.demo.evaluation.domain.model.Exam;
import com.tomas.demo.evaluation.domain.repository.ExamRepository;
import com.tomas.demo.faculty.domain.model.Teacher;
import com.tomas.demo.faculty.domain.repository.TeacherRepository;
import com.tomas.demo.shared.domain.exception.BusinessValidationException;
import com.tomas.demo.shared.domain.exception.EntityNotFoundException;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExamApplicationServiceTest {

    @Mock
    private ExamRepository examRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private CareerRepository careerRepository;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Spy
    private ExamMapper examMapper = Mappers.getMapper(ExamMapper.class);

    @InjectMocks
    private ExamApplicationService examApplicationService;

    private UUID teacherId;
    private UUID studentId;
    private UUID subjectId;
    private UUID careerId;
    private UUID examId;

    @BeforeEach
    void setUp() {
        teacherId = UUID.randomUUID();
        studentId = UUID.randomUUID();
        subjectId = UUID.randomUUID();
        careerId = UUID.randomUUID();
        examId = UUID.randomUUID();
    }

    @Test
    void registerGrade_Success() {
        RegisterGradeCommand command = new RegisterGradeCommand(
                teacherId,
                studentId,
                subjectId,
                careerId,
                9.5,
                LocalDate.now(),
                "Midterm exam"
        );

        when(teacherRepository.existsById(teacherId)).thenReturn(true);
        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(subjectRepository.existsById(subjectId)).thenReturn(true);
        when(careerRepository.existsById(careerId)).thenReturn(true);
        when(enrollmentRepository.existsByStudentIdAndSubjectIdAndCareerIdAndStatus(
                studentId, subjectId, careerId, EnrollmentStatus.ACTIVE)).thenReturn(true);

        Exam savedExam = new Exam(
                examId,
                teacherId,
                studentId,
                subjectId,
                careerId,
                LocalDate.now(),
                9.5,
                "Midterm exam",
                true
        );
        when(examRepository.save(any(Exam.class))).thenReturn(savedExam);

        ExamDTO result = examApplicationService.registerGrade(command);

        assertNotNull(result);
        assertEquals(examId, result.id());
        assertEquals(teacherId, result.teacherId());
        assertEquals(studentId, result.studentId());
        assertEquals(subjectId, result.subjectId());
        assertEquals(careerId, result.careerId());
        assertEquals(9.5, result.grade());
        verify(examRepository, times(1)).save(any(Exam.class));
    }

    @Test
    void registerGrade_StudentNotEnrolled_ThrowsException() {
        RegisterGradeCommand command = new RegisterGradeCommand(
                teacherId,
                studentId,
                subjectId,
                careerId,
                8.0,
                LocalDate.now(),
                "Final Exam"
        );

        when(teacherRepository.existsById(teacherId)).thenReturn(true);
        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(subjectRepository.existsById(subjectId)).thenReturn(true);
        when(careerRepository.existsById(careerId)).thenReturn(true);
        when(enrollmentRepository.existsByStudentIdAndSubjectIdAndCareerIdAndStatus(
                studentId, subjectId, careerId, EnrollmentStatus.ACTIVE)).thenReturn(false);

        assertThrows(BusinessValidationException.class, () -> examApplicationService.registerGrade(command));
        verify(examRepository, never()).save(any(Exam.class));
    }

    @Test
    void bulkRegisterGrades_Success() {
        UUID student2Id = UUID.randomUUID();
        BulkGradeUploadRequest request = new BulkGradeUploadRequest(
                teacherId,
                subjectId,
                careerId,
                LocalDate.now(),
                List.of(
                        new StudentGradeItem(studentId, 8.5, "Good"),
                        new StudentGradeItem(student2Id, 7.0, "Approved")
                )
        );

        when(teacherRepository.existsById(teacherId)).thenReturn(true);
        when(subjectRepository.existsById(subjectId)).thenReturn(true);
        when(careerRepository.existsById(careerId)).thenReturn(true);
        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(studentRepository.existsById(student2Id)).thenReturn(true);
        when(enrollmentRepository.existsByStudentIdAndSubjectIdAndCareerIdAndStatus(
                studentId, subjectId, careerId, EnrollmentStatus.ACTIVE)).thenReturn(true);
        when(enrollmentRepository.existsByStudentIdAndSubjectIdAndCareerIdAndStatus(
                student2Id, subjectId, careerId, EnrollmentStatus.ACTIVE)).thenReturn(true);

        when(examRepository.saveAll(any())).thenAnswer(invocation -> invocation.getArgument(0));

        List<ExamDTO> result = examApplicationService.bulkRegisterGrades(request);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(examRepository, times(1)).saveAll(any());
    }

    @Test
    void getExamDetailById_Success() {
        Exam exam = new Exam(
                examId,
                teacherId,
                studentId,
                subjectId,
                careerId,
                LocalDate.now(),
                9.0,
                "Final",
                true
        );

        Teacher teacher = new Teacher(teacherId, "Prof. Gauss", "T-01", "gauss@uni.edu", "11111111", true);
        Student student = new Student(studentId, "Alan Turing", "turing@uni.edu", "22222222", "pass", 1001, true);
        Subject subject = new Subject(subjectId, "Algorithms", "CS101", "CS Fundamentals", 6, 1, true);
        Career career = new Career(careerId, "Computer Science", "CS", "CS Degree", true);

        when(examRepository.findById(examId)).thenReturn(Optional.of(exam));
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));
        when(careerRepository.findById(careerId)).thenReturn(Optional.of(career));

        ExamDetailDTO detail = examApplicationService.getExamDetailById(examId);

        assertNotNull(detail);
        assertEquals(examId, detail.id());
        assertEquals("Prof. Gauss", detail.teacherName());
        assertEquals("Alan Turing", detail.studentName());
        assertEquals("Algorithms", detail.subjectName());
        assertEquals("Computer Science", detail.careerName());
        assertEquals(9.0, detail.grade());
    }
}
