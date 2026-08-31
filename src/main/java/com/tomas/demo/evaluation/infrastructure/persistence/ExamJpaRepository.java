package com.tomas.demo.evaluation.infrastructure.persistence;

import com.tomas.demo.evaluation.domain.model.Exam;
import com.tomas.demo.evaluation.domain.repository.ExamRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExamJpaRepository extends JpaRepository<Exam, UUID>, ExamRepository {
    @Override
    List<Exam> findByStudentId(UUID studentId);

    @Override
    List<Exam> findBySubjectId(UUID subjectId);

    @Override
    List<Exam> findByTeacherId(UUID teacherId);

    @Override
    List<Exam> findBySubjectIdAndCareerId(UUID subjectId, UUID careerId);

    @Override
    List<Exam> findBySubjectIdAndCareerIdAndTeacherId(UUID subjectId, UUID careerId, UUID teacherId);
}
