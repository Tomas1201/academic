package com.tomas.demo.attendance.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "attendances")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Decoupled foreign UUID references
    @NotNull
    @Column(name = "student_id", nullable = false)
    private UUID studentId;

    @NotNull
    @Column(name = "subject_id", nullable = false)
    private UUID subjectId;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "attendance_value", nullable = false)
    private int value; // 1 = Present, 0 = Absent, etc.

    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active = true;

    public Attendance() {
    }

    public Attendance(UUID id, UUID studentId, UUID subjectId, LocalDate date, int value, boolean active) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.date = date != null ? date : LocalDate.now();
        this.value = value;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = subjectId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
