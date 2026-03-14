package com.tomas.demo.features.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import errors.ResourceNotFoundException;

@Service
public class teacherService {

    @Autowired
    private teacherRepository teacherRepository;

    public teacherDTO getTeacher(int id) {
        return teacherMapper.toDTO(teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found")));
    }

    public teacherDTO createTeacher(teacherModel teacher) {
        return teacherMapper.toDTO(teacherRepository.save(teacher));
    }

    public teacherDTO updateTeacher(teacherModel teacher) {
        return teacherMapper.toDTO(teacherRepository.save(teacher));
    }

    public teacherDTO deleteTeacher(int id) {
        teacherRepository.deleteById(id);
        return null;
    }
}
