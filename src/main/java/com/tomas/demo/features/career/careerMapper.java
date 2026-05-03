package com.tomas.demo.features.career;

public class careerMapper {
    public static careerDTO toDTO(careerModel career) {
        return new careerDTO(career.getId(), career.getName(), career.getCode(), career.getDescription());
    }

    public static careerModel toModel(careerDTO career) {
        return new careerModel(career.id(), career.name(), career.code(), career.description());
    }
}
