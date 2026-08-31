package com.tomas.demo.attendance.application.service;

import com.tomas.demo.attendance.application.dto.AttendanceCreateRequest;
import com.tomas.demo.attendance.application.dto.AttendanceDTO;
import com.tomas.demo.attendance.application.mapper.AttendanceMapper;
import com.tomas.demo.attendance.domain.model.Attendance;
import com.tomas.demo.attendance.domain.repository.AttendanceRepository;
import com.tomas.demo.curriculum.domain.repository.SubjectRepository;
import com.tomas.demo.shared.domain.exception.EntityNotFoundException;
import com.tomas.demo.student.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AttendanceApplicationService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceApplicationService(
            AttendanceRepository attendanceRepository,
            StudentRepository studentRepository,
            SubjectRepository subjectRepository,
            AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.attendanceMapper = attendanceMapper;
    }

    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAllAttendances() {
        return attendanceMapper.toDtoList(attendanceRepository.findAll());
    }

    @Transactional(readOnly = true)
    public AttendanceDTO getAttendanceById(UUID id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance", id));
        return attendanceMapper.toDto(attendance);
    }

    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendancesByStudent(UUID studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new EntityNotFoundException("Student", studentId);
        }
        return attendanceMapper.toDtoList(attendanceRepository.findByStudentId(studentId));
    }

    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendancesBySubject(UUID subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new EntityNotFoundException("Subject", subjectId);
        }
        return attendanceMapper.toDtoList(attendanceRepository.findBySubjectId(subjectId));
    }

    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendancesBySubjectAndDate(UUID subjectId, LocalDate date) {
        return attendanceMapper.toDtoList(attendanceRepository.findBySubjectIdAndDate(subjectId, date));
    }

    public AttendanceDTO recordAttendance(AttendanceCreateRequest request) {
        if (!studentRepository.existsById(request.studentId())) {
            throw new EntityNotFoundException("Student", request.studentId());
        }
        if (!subjectRepository.existsById(request.subjectId())) {
            throw new EntityNotFoundException("Subject", request.subjectId());
        }

        Attendance attendance = new Attendance(
                null,
                request.studentId(),
                request.subjectId(),
                request.date() != null ? request.date() : LocalDate.now(),
                request.value(),
                true
        );

        Attendance saved = attendanceRepository.save(attendance);
        return attendanceMapper.toDto(saved);
    }

    public void deleteAttendance(UUID id) {
        if (!attendanceRepository.existsById(id)) {
            throw new EntityNotFoundException("Attendance", id);
        }
        attendanceRepository.deleteById(id);
    }
}
