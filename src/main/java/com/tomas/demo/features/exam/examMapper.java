package com.tomas.demo.features.exam;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface examMapper {

    examMapper INSTANCE = Mappers.getMapper(examMapper.class);

    examDTO toDto(examModel examModel);
    
    examModel toModel(examDTO examDTO);

    List<examDTO> toDtoList(List<examModel> examModels);
}
