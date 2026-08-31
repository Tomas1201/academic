package com.tomas.demo.evaluation.domain.repository;

import com.tomas.demo.evaluation.domain.model.Exam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExamRepository {
    <S extends Exam> S save(S exam);
    <S extends Exam> List<S> saveAll(Iterable<S> exams);
    Optional<Exam> findById(UUID id);
    List<Exam> findAll();
    List<Exam> findByStudentId(UUID studentId);
    List<Exam> findBySubjectId(UUID subjectId);
    List<Exam> findByTeacherId(UUID teacherId);
    List<Exam> findBySubjectIdAndCareerId(UUID subjectId, UUID careerId);
    List<Exam> findBySubjectIdAndCareerIdAndTeacherId(UUID subjectId, UUID careerId, UUID teacherId);
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
