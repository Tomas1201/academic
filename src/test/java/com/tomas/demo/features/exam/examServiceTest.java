package com.tomas.demo.features.exam;

import com.tomas.demo.errors.ResourceNotFoundException;
import com.tomas.demo.features.student.studentRepository;
import com.tomas.demo.features.subject.subjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class examServiceTest {

    @Mock
    private examRepository examRepository;

    @Mock
    private studentRepository studentRepository;

    @Mock
    private subjectRepository subjectRepository;

    @InjectMocks
    private examService examService;

    private UUID examId;
    private UUID studentId;
    private UUID subjectId;
    private examModel exam;
    private examDTO examDto;

    @BeforeEach
    void setUp() {
        examId = UUID.randomUUID();
        studentId = UUID.randomUUID();
        subjectId = UUID.randomUUID();

        exam = new examModel();
        exam.setId(examId);
        exam.setStudentId(studentId);
        exam.setSubjectId(subjectId);
        exam.setExamDate(LocalDate.now());
        exam.setGrade(8.5);
        exam.setNotes("First Partial");

        examDto = new examDTO(examId, studentId, subjectId, LocalDate.now(), 8.5, "First Partial");
    }

    @Test
    void getExamById_Success() {
        when(examRepository.findById(examId)).thenReturn(Optional.of(exam));

        examDTO result = examService.getExamById(examId);

        assertNotNull(result);
        assertEquals(examId, result.id());
        assertEquals(studentId, result.studentId());
        assertEquals(subjectId, result.subjectId());
        assertEquals(8.5, result.grade());
    }

    @Test
    void getExamById_NotFound() {
        when(examRepository.findById(examId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> examService.getExamById(examId));
    }

    @Test
    void createExam_Success() {
        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(subjectRepository.existsById(subjectId)).thenReturn(true);
        when(examRepository.save(any(examModel.class))).thenReturn(exam);

        examDTO result = examService.createExam(examDto);

        assertNotNull(result);
        assertEquals(studentId, result.studentId());
        assertEquals(subjectId, result.subjectId());
        assertEquals(8.5, result.grade());
        verify(examRepository, times(1)).save(any(examModel.class));
    }

    @Test
    void createExam_StudentNotFound() {
        when(studentRepository.existsById(studentId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> examService.createExam(examDto));
        verify(examRepository, never()).save(any(examModel.class));
    }

    @Test
    void deleteExam_Success() {
        when(examRepository.existsById(examId)).thenReturn(true);
        doNothing().when(examRepository).deleteById(examId);

        assertDoesNotThrow(() -> examService.deleteExam(examId));

        verify(examRepository, times(1)).deleteById(examId);
    }

    @Test
    void deleteExam_NotFound() {
        when(examRepository.existsById(examId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> examService.deleteExam(examId));
        verify(examRepository, never()).deleteById(any(UUID.class));
    }
}
