package com.tomas.demo.curriculum.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "career_subjects")
public class CareerSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @NotNull
    @Column(name = "semester", nullable = false)
    private int semester;

    @NotNull
    @Column(name = "study_year", nullable = false)
    private int studyYear;

    @NotNull
    @Column(name = "mandatory", nullable = false)
    private boolean mandatory = true;

    public CareerSubject() {
    }

    public CareerSubject(Career career, Subject subject, int semester, int studyYear, boolean mandatory) {
        this.career = career;
        this.subject = subject;
        this.semester = semester;
        this.studyYear = studyYear;
        this.mandatory = mandatory;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
}
