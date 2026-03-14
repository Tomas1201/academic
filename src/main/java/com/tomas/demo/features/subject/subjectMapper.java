package com.tomas.demo.features.subject;

public class subjectMapper {
    public static subjectDTO toDTO(subjectModel subject) {
        return new subjectDTO(subject.getId(), subject.getName(), subject.getCode(), subject.getDescription(), subject.getCredits(), subject.getSemester());
    }
}
