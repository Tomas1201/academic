package com.tomas.demo.enrollment.application.mapper;

import com.tomas.demo.enrollment.application.dto.EnrollmentCreateRequest;
import com.tomas.demo.enrollment.application.dto.EnrollmentDTO;
import com.tomas.demo.enrollment.domain.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    EnrollmentDTO toDto(Enrollment model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollmentDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "active", constant = "true")
    Enrollment toModel(EnrollmentCreateRequest request);

    List<EnrollmentDTO> toDtoList(List<Enrollment> models);
}
