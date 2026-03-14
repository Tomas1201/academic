package com.tomas.demo.features.teacher;

public class teacherMapper {
    
    public static teacherDTO toDTO(teacherModel teacher) {
        return new teacherDTO(
            teacher.getId(),
            teacher.getName(),
            teacher.getCode(),
            teacher.getDescription(),
            teacher.getCredits(),
            teacher.getSemester()
        );
    }
}
