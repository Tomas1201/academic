package com.tomas.demo.student.application.service;

import com.tomas.demo.shared.domain.exception.BusinessValidationException;
import com.tomas.demo.shared.domain.exception.EntityNotFoundException;
import com.tomas.demo.student.application.dto.StudentCreateRequest;
import com.tomas.demo.student.application.dto.StudentDTO;
import com.tomas.demo.student.application.mapper.StudentMapper;
import com.tomas.demo.student.domain.model.Student;
import com.tomas.demo.student.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentApplicationService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentApplicationService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        return studentMapper.toDtoList(studentRepository.findAll());
    }

    @Transactional(readOnly = true)
    public StudentDTO getStudentById(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));
        return studentMapper.toDto(student);
    }

    public StudentDTO createStudent(StudentCreateRequest request) {
        if (studentRepository.findByDni(request.dni()).isPresent()) {
            throw new BusinessValidationException("Student with DNI " + request.dni() + " already exists");
        }
        if (studentRepository.findByEmail(request.email()).isPresent()) {
            throw new BusinessValidationException("Student with email " + request.email() + " already exists");
        }

        Student student = studentMapper.toModel(request);
        Student saved = studentRepository.save(student);
        return studentMapper.toDto(saved);
    }

    public StudentDTO updateStudent(UUID id, StudentCreateRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));

        student.setName(request.name());
        student.setEmail(request.email());
        student.setDni(request.dni());
        student.setPassword(request.password());
        student.setFileNumber(request.fileNumber());

        Student updated = studentRepository.save(student);
        return studentMapper.toDto(updated);
    }

    public void deleteStudent(UUID id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student", id);
        }
        studentRepository.deleteById(id);
    }
}
