package com.tomas.demo.curriculum.application.mapper;

import com.tomas.demo.curriculum.application.dto.CareerDTO;
import com.tomas.demo.curriculum.domain.model.Career;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CareerMapper {
    CareerMapper INSTANCE = Mappers.getMapper(CareerMapper.class);

    CareerDTO toDto(Career model);

    @org.mapstruct.Mapping(target = "studyPlan", ignore = true)
    Career toModel(CareerDTO dto);
    List<CareerDTO> toDtoList(List<Career> models);
}
