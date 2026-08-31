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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamApplicationService {

    private final ExamRepository examRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final CareerRepository careerRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final ExamMapper examMapper;

    public ExamApplicationService(
            ExamRepository examRepository,
            TeacherRepository teacherRepository,
            StudentRepository studentRepository,
            SubjectRepository subjectRepository,
            CareerRepository careerRepository,
            EnrollmentRepository enrollmentRepository,
            ExamMapper examMapper) {
        this.examRepository = examRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.careerRepository = careerRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.examMapper = examMapper;
    }

    @Transactional(readOnly = true)
    public List<ExamDTO> getAllExams() {
        return examMapper.toDtoList(examRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ExamDTO getExamById(UUID id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam", id));
        return examMapper.toDto(exam);
    }

    @Transactional(readOnly = true)
    public ExamDetailDTO getExamDetailById(UUID id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam", id));

        Teacher teacher = teacherRepository.findById(exam.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher", exam.getTeacherId()));

        Student student = studentRepository.findById(exam.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student", exam.getStudentId()));

        Subject subject = subjectRepository.findById(exam.getSubjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject", exam.getSubjectId()));

        Career career = careerRepository.findById(exam.getCareerId())
                .orElseThrow(() -> new EntityNotFoundException("Career", exam.getCareerId()));

        return new ExamDetailDTO(
                exam.getId(),
                teacher.getId(),
                teacher.getName(),
                student.getId(),
                student.getName(),
                student.getDni(),
                student.getFileNumber(),
                subject.getId(),
                subject.getName(),
                subject.getCode(),
                career.getId(),
                career.getName(),
                exam.getExamDate(),
                exam.getGrade(),
                exam.getNotes(),
                exam.isActive()
        );
    }

    @Transactional(readOnly = true)
    public List<ExamDTO> getExamsByStudent(UUID studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new EntityNotFoundException("Student", studentId);
        }
        return examMapper.toDtoList(examRepository.findByStudentId(studentId));
    }

    @Transactional(readOnly = true)
    public List<ExamDTO> getExamsBySubjectAndCareer(UUID subjectId, UUID careerId) {
        return examMapper.toDtoList(examRepository.findBySubjectIdAndCareerId(subjectId, careerId));
    }

    @Transactional(readOnly = true)
    public List<ExamDTO> getExamsByTeacherAndSubjectAndCareer(UUID teacherId, UUID subjectId, UUID careerId) {
        return examMapper.toDtoList(examRepository.findBySubjectIdAndCareerIdAndTeacherId(subjectId, careerId, teacherId));
    }

    /**
     * Use case: A teacher uploads/registers the exam grade of a student in a specific subject of a specific career.
     */
    public ExamDTO registerGrade(RegisterGradeCommand command) {
        // 1. Validate teacher
        if (!teacherRepository.existsById(command.teacherId())) {
            throw new EntityNotFoundException("Teacher", command.teacherId());
        }

        // 2. Validate student
        if (!studentRepository.existsById(command.studentId())) {
            throw new EntityNotFoundException("Student", command.studentId());
        }

        // 3. Validate subject and career
        if (!subjectRepository.existsById(command.subjectId())) {
            throw new EntityNotFoundException("Subject", command.subjectId());
        }
        if (!careerRepository.existsById(command.careerId())) {
            throw new EntityNotFoundException("Career", command.careerId());
        }

        // 4. Validate active enrollment of student in this subject and career
        if (!enrollmentRepository.existsByStudentIdAndSubjectIdAndCareerIdAndStatus(
                command.studentId(), command.subjectId(), command.careerId(), EnrollmentStatus.ACTIVE)) {
            throw new BusinessValidationException("Student is not actively enrolled in this subject for the specified career");
        }

        Exam exam = new Exam(
                null,
                command.teacherId(),
                command.studentId(),
                command.subjectId(),
                command.careerId(),
                command.examDate() != null ? command.examDate() : LocalDate.now(),
                command.grade(),
                command.notes(),
                true
        );

        Exam saved = examRepository.save(exam);
        return examMapper.toDto(saved);
    }

    /**
     * Use case: Bulk grade upload by a teacher for a class in a specific subject and career.
     */
    public List<ExamDTO> bulkRegisterGrades(BulkGradeUploadRequest request) {
        // Validate teacher, subject and career
        if (!teacherRepository.existsById(request.teacherId())) {
            throw new EntityNotFoundException("Teacher", request.teacherId());
        }
        if (!subjectRepository.existsById(request.subjectId())) {
            throw new EntityNotFoundException("Subject", request.subjectId());
        }
        if (!careerRepository.existsById(request.careerId())) {
            throw new EntityNotFoundException("Career", request.careerId());
        }

        LocalDate date = request.examDate() != null ? request.examDate() : LocalDate.now();
        List<Exam> examsToSave = new ArrayList<>();

        for (StudentGradeItem item : request.grades()) {
            if (!studentRepository.existsById(item.studentId())) {
                throw new EntityNotFoundException("Student", item.studentId());
            }

            if (!enrollmentRepository.existsByStudentIdAndSubjectIdAndCareerIdAndStatus(
                    item.studentId(), request.subjectId(), request.careerId(), EnrollmentStatus.ACTIVE)) {
                throw new BusinessValidationException("Student " + item.studentId() + " is not actively enrolled in this subject and career");
            }

            Exam exam = new Exam(
                    null,
                    request.teacherId(),
                    item.studentId(),
                    request.subjectId(),
                    request.careerId(),
                    date,
                    item.grade(),
                    item.notes(),
                    true
            );
            examsToSave.add(exam);
        }

        List<Exam> saved = examRepository.saveAll(examsToSave);
        return examMapper.toDtoList(saved);
    }

    public void deleteExam(UUID id) {
        if (!examRepository.existsById(id)) {
            throw new EntityNotFoundException("Exam", id);
        }
        examRepository.deleteById(id);
    }
}
