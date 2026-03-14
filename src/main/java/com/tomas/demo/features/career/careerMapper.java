package com.tomas.demo.features.career;

public class careerMapper {
    public static careerDTO toDTO(careerModel career) {
        return new careerDTO(career.getId(), career.getName(), career.getCode(), career.getDescription());
    }
}
