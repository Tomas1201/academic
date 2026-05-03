package com.tomas.demo.features.Attendance;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "attendance_students")
public class AttendanceStudent {
    @Id
    private UUID id;  // el mismo ID que tiene en Enrollment

    private String fullName;
    private UUID subjectId;
    private String period;  // ej: "2024-1C"

   // @Enumerated(EnumType.STRING)
    //private AttendanceStudentStatus status;

}