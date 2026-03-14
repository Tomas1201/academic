package com.tomas.demo.features.student;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import errors.ResourceNotFoundException;

@Service
public class studentService {

    @Autowired
    private studentRepository studentRepository;

    public studentDTO getStudent(int id) {
        
        return studentMapper.toDTO(studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found")));
    }

    public studentDTO createStudent(studentModel student) {
        return studentMapper.toDTO(studentRepository.save(student));
    }

    public studentDTO updateStudent(studentModel student) {
        return studentMapper.toDTO(studentRepository.save(student));
    }

    public studentDTO deleteStudent(int id) {
        studentRepository.deleteById(id);
        return null;
    }
}