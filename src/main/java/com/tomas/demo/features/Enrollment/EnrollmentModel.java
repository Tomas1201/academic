package com.tomas.demo.features.Enrollment;

import com.tomas.demo.features.student.studentModel;
import com.tomas.demo.features.subject.subjectModel;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "enrollments")
public class EnrollmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "student_id", nullable = false)
    private UUID student_id;

    @Column(name = "subject_id", nullable = false)
    private UUID subject_id;

    @Column(name = "career_id", nullable = false)
    private UUID career_id;

    @Column(nullable = false)
    private LocalDate enrollmentDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStudent_id() {
        return student_id;
    }

    public void setStudent_id(UUID student_id) {
        this.student_id = student_id;
    }

    public UUID getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(UUID subject_id) {
        this.subject_id = subject_id;
    }

    public UUID getCareer_id() {
        return career_id;
    }

    public void setCareer_id(UUID career_id) {
        this.career_id = career_id;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
