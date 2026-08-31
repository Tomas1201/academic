package com.tomas.demo.attendance.application.mapper;

import com.tomas.demo.attendance.application.dto.AttendanceCreateRequest;
import com.tomas.demo.attendance.application.dto.AttendanceDTO;
import com.tomas.demo.attendance.domain.model.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    AttendanceDTO toDto(Attendance model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Attendance toModel(AttendanceCreateRequest request);

    List<AttendanceDTO> toDtoList(List<Attendance> models);
}
