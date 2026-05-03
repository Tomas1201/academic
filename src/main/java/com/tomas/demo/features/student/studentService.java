package com.tomas.demo.features.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tomas.demo.errors.ResourceNotFoundException;

import java.util.UUID;

@Service
public class studentService {

    @Autowired
    private studentRepository studentRepository;

    public studentDTO getStudent(UUID id) {
        
        return studentMapper.toDTO(studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found")));
    }

    public studentDTO createStudent(studentModel student) {
        return studentMapper.toDTO(studentRepository.save(student));
    }

    public studentDTO updateStudent(studentModel student) {
        return studentMapper.toDTO(studentRepository.save(student));
    }

    public studentDTO deleteStudent(UUID id) {
        studentRepository.deleteById(id);
        return null;
    }
}