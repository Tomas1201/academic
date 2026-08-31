package com.tomas.demo.evaluation.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Decoupled foreign references by UUID
    @NotNull
    @Column(name = "teacher_id", nullable = false)
    private UUID teacherId;

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
    @Column(name = "exam_date", nullable = false)
    private LocalDate examDate;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Column(name = "grade", nullable = false)
    private Double grade;

    @Size(max = 255)
    @Column(name = "notes")
    private String notes;

    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active = true;

    public Exam() {
    }

    public Exam(UUID id, UUID teacherId, UUID studentId, UUID subjectId, UUID careerId, LocalDate examDate, Double grade, String notes, boolean active) {
        this.id = id;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.careerId = careerId;
        this.examDate = examDate != null ? examDate : LocalDate.now();
        this.grade = grade;
        this.notes = notes;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
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

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
