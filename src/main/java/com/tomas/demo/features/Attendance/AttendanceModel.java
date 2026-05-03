package com.tomas.demo.features.Attendance;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "assistance")
public class AttendanceModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private int student_id;

    @NotNull
    @Column(name = "subject_id", nullable = false)
    private int subject_id;

    @NotNull
    @Column(name = "date", nullable = false)
    private Date date;

    @NotNull
    @Column(name = "value", nullable = false)
    private int value;

    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private AttendanceStudent student;
*/
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }



    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}