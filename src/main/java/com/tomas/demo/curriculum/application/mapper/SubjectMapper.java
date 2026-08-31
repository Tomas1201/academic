package com.tomas.demo.curriculum.application.mapper;

import com.tomas.demo.curriculum.application.dto.SubjectDTO;
import com.tomas.demo.curriculum.domain.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectDTO toDto(Subject model);

    @org.mapstruct.Mapping(target = "careerSubjects", ignore = true)
    Subject toModel(SubjectDTO dto);
    List<SubjectDTO> toDtoList(List<Subject> models);
}
