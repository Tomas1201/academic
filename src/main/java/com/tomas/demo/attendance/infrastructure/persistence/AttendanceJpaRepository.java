package com.tomas.demo.attendance.infrastructure.persistence;

import com.tomas.demo.attendance.domain.model.Attendance;
import com.tomas.demo.attendance.domain.repository.AttendanceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceJpaRepository extends JpaRepository<Attendance, UUID>, AttendanceRepository {
    @Override
    List<Attendance> findByStudentId(UUID studentId);

    @Override
    List<Attendance> findBySubjectId(UUID subjectId);

    @Override
    List<Attendance> findBySubjectIdAndDate(UUID subjectId, LocalDate date);
}
