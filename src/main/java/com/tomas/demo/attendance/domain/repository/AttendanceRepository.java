package com.tomas.demo.attendance.domain.repository;

import com.tomas.demo.attendance.domain.model.Attendance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendanceRepository {
    <S extends Attendance> S save(S attendance);
    <S extends Attendance> List<S> saveAll(Iterable<S> attendances);
    Optional<Attendance> findById(UUID id);
    List<Attendance> findAll();
    List<Attendance> findByStudentId(UUID studentId);
    List<Attendance> findBySubjectId(UUID subjectId);
    List<Attendance> findBySubjectIdAndDate(UUID subjectId, LocalDate date);
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
