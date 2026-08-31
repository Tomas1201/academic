package com.tomas.demo.student.application.mapper;

import com.tomas.demo.student.application.dto.StudentCreateRequest;
import com.tomas.demo.student.application.dto.StudentDTO;
import com.tomas.demo.student.domain.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO toDto(Student model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Student toModel(StudentCreateRequest request);

    List<StudentDTO> toDtoList(List<Student> models);
}
