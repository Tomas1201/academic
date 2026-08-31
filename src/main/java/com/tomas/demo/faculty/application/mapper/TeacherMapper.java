package com.tomas.demo.faculty.application.mapper;

import com.tomas.demo.faculty.application.dto.TeacherCreateRequest;
import com.tomas.demo.faculty.application.dto.TeacherDTO;
import com.tomas.demo.faculty.domain.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    TeacherDTO toDto(Teacher model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Teacher toModel(TeacherCreateRequest request);

    List<TeacherDTO> toDtoList(List<Teacher> models);
}
