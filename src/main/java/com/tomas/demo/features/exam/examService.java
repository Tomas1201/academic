package com.tomas.demo.features.exam;

import com.tomas.demo.errors.ResourceNotFoundException;
import com.tomas.demo.features.student.studentRepository;
import com.tomas.demo.features.subject.subjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class examService {

    @Autowired
    private examRepository examRepository;

    @Autowired
    private studentRepository studentRepository;

    @Autowired
    private subjectRepository subjectRepository;

    private final examMapper examMapper = com.tomas.demo.features.exam.examMapper.INSTANCE;

    public List<examDTO> getAllExams() {
        return examMapper.toDtoList(examRepository.findAll());
    }

    public examDTO getExamById(UUID id) {
        examModel exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id: " + id));
        return examMapper.toDto(exam);
    }

    public List<examDTO> getExamsByStudentId(UUID studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }
        return examMapper.toDtoList(examRepository.findByStudentId(studentId));
    }

    public List<examDTO> getExamsBySubjectId(UUID subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new ResourceNotFoundException("Subject not found with id: " + subjectId);
        }
        return examMapper.toDtoList(examRepository.findBySubjectId(subjectId));
    }

    public examDTO createExam(examDTO examDto) {
        if (!studentRepository.existsById(examDto.studentId())) {
            throw new ResourceNotFoundException("Student not found with id: " + examDto.studentId());
        }
        if (!subjectRepository.existsById(examDto.subjectId())) {
            throw new ResourceNotFoundException("Subject not found with id: " + examDto.subjectId());
        }

        examModel exam = examMapper.toModel(examDto);
        if (exam.getExamDate() == null) {
            exam.setExamDate(java.time.LocalDate.now());
        }
        examModel savedExam = examRepository.save(exam);
        return examMapper.toDto(savedExam);
    }

    public examDTO updateExam(UUID id, examDTO examDto) {
        examModel existingExam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id: " + id));

        if (!studentRepository.existsById(examDto.studentId())) {
            throw new ResourceNotFoundException("Student not found with id: " + examDto.studentId());
        }
        if (!subjectRepository.existsById(examDto.subjectId())) {
            throw new ResourceNotFoundException("Subject not found with id: " + examDto.subjectId());
        }

        existingExam.setStudentId(examDto.studentId());
        existingExam.setSubjectId(examDto.subjectId());
        existingExam.setExamDate(examDto.examDate() != null ? examDto.examDate() : java.time.LocalDate.now());
        existingExam.setGrade(examDto.grade());
        existingExam.setNotes(examDto.notes());

        examModel updatedExam = examRepository.save(existingExam);
        return examMapper.toDto(updatedExam);
    }

    public void deleteExam(UUID id) {
        if (!examRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exam not found with id: " + id);
        }
        examRepository.deleteById(id);
    }
}
