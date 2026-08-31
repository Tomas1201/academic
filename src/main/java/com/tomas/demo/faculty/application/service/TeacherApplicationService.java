package com.tomas.demo.faculty.application.service;

import com.tomas.demo.faculty.application.dto.TeacherCreateRequest;
import com.tomas.demo.faculty.application.dto.TeacherDTO;
import com.tomas.demo.faculty.application.mapper.TeacherMapper;
import com.tomas.demo.faculty.domain.model.Teacher;
import com.tomas.demo.faculty.domain.repository.TeacherRepository;
import com.tomas.demo.shared.domain.exception.BusinessValidationException;
import com.tomas.demo.shared.domain.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TeacherApplicationService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherApplicationService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Transactional(readOnly = true)
    public List<TeacherDTO> getAllTeachers() {
        return teacherMapper.toDtoList(teacherRepository.findAll());
    }

    @Transactional(readOnly = true)
    public TeacherDTO getTeacherById(UUID id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher", id));
        return teacherMapper.toDto(teacher);
    }

    public TeacherDTO createTeacher(TeacherCreateRequest request) {
        if (teacherRepository.findByCode(request.code()).isPresent()) {
            throw new BusinessValidationException("Teacher with code " + request.code() + " already exists");
        }
        if (teacherRepository.findByEmail(request.email()).isPresent()) {
            throw new BusinessValidationException("Teacher with email " + request.email() + " already exists");
        }

        Teacher teacher = teacherMapper.toModel(request);
        Teacher saved = teacherRepository.save(teacher);
        return teacherMapper.toDto(saved);
    }

    public TeacherDTO updateTeacher(UUID id, TeacherCreateRequest request) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher", id));

        teacher.setName(request.name());
        teacher.setCode(request.code());
        teacher.setEmail(request.email());
        teacher.setDni(request.dni());

        Teacher updated = teacherRepository.save(teacher);
        return teacherMapper.toDto(updated);
    }

    public void deleteTeacher(UUID id) {
        if (!teacherRepository.existsById(id)) {
            throw new EntityNotFoundException("Teacher", id);
        }
        teacherRepository.deleteById(id);
    }
}
