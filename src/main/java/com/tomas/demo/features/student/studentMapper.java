package com.tomas.demo.features.student;

import java.util.Optional;

public class studentMapper {

    public static studentDTO toDTO(studentModel model) {
        return new studentDTO(model.getName(), model.getEmail(), model.getDni(), model.getFile());
    }
}