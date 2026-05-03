package com.tomas.demo.features.Enrollment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnrollmentMapper {

    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    @Mapping(source = "student_id", target = "studentId")
    @Mapping(source = "subject_id", target = "subjectId")
    @Mapping(source = "career_id", target = "careerId")
    EnrollmentDTO toDto(EnrollmentModel enrollmentModel);

    @Mapping(source = "studentId", target = "student_id")
    @Mapping(source = "subjectId", target = "subject_id")
    @Mapping(source = "careerId", target = "career_id")
    EnrollmentModel toModel(EnrollmentDTO enrollmentDTO);
}
