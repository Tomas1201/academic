package com.tomas.demo.enrollment.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Decoupled references via UUID IDs
    @NotNull
    @Column(name = "student_id", nullable = false)
    private UUID studentId;

    @NotNull
    @Column(name = "subject_id", nullable = false)
    private UUID subjectId;

    @NotNull
    @Column(name = "career_id", nullable = false)
    private UUID careerId;

    @NotNull
    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;

    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active = true;

    public Enrollment() {
    }

    public Enrollment(UUID id, UUID studentId, UUID subjectId, UUID careerId, LocalDate enrollmentDate, EnrollmentStatus status, boolean active) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.careerId = careerId;
        this.enrollmentDate = enrollmentDate;
        this.status = status != null ? status : EnrollmentStatus.ACTIVE;
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

    public UUID getCareerId() {
        return careerId;
    }

    public void setCareerId(UUID careerId) {
        this.careerId = careerId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
