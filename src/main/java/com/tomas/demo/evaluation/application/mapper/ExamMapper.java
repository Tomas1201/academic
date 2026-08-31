package com.tomas.demo.evaluation.application.mapper;

import com.tomas.demo.evaluation.application.dto.ExamDTO;
import com.tomas.demo.evaluation.application.dto.RegisterGradeCommand;
import com.tomas.demo.evaluation.domain.model.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    ExamMapper INSTANCE = Mappers.getMapper(ExamMapper.class);

    ExamDTO toDto(Exam model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Exam toModel(RegisterGradeCommand command);

    List<ExamDTO> toDtoList(List<Exam> models);
}
