package com.tomas.demo.features.Attendance;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
/*
// Attendance escucha el evento y arma su propio modelo
@Component
public class AttendanceEnrollmentListener {

    @EventListener
    public void onStudentEnrolled(StudentEnrolledEvent event) {
        // Crea su propia representación del estudiante
        // con solo lo que necesita
        AttendanceStudent student = new AttendanceStudent(
                event.getStudentId(),
                event.getFullName(),
                event.getSubjectId(),
                event.getPeriod()
        );
        attendanceStudentRepository.save(student);
    }
}
*/