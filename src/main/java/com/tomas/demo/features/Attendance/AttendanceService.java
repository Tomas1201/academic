package com.tomas.demo.features.Attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository AttendanceRepository;
/*
    public List<AttendanceListDTO> getAttendanceListForSubject(UUID subjectId) {
        return AttendanceRepository.findBySubjectId(subjectId);
    }
*/
    public Optional<AttendanceModel> getAttendance(int id) {
        return AttendanceRepository.findById(id);}

    public AttendanceModel createAttendance(AttendanceModel assistance) {
        return AttendanceRepository.save(assistance);
    }

    public AttendanceModel updateAttendance(AttendanceModel assistance) {
        return AttendanceRepository.save(assistance);
    }

    public AttendanceModel deleteAttendance(int id) {
        AttendanceRepository.deleteById(id);
        return null;
    }
}
