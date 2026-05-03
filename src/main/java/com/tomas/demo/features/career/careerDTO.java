package com.tomas.demo.features.career;

public record careerDTO(int id, String name, String code, String description) {
    public static careerDTO fromModel(careerModel model) {
        return new careerDTO(model.getId(), model.getName(), model.getCode(), model.getDescription());
    }
}
