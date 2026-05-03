package com.tomas.demo.features.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomas.demo.errors.ResourceNotFoundException;

import java.util.UUID;

@Service
public class subjectService {

    @Autowired
    private subjectRepository subjectRepository;

    public subjectDTO getSubject(UUID id) {
        return subjectMapper.toDTO(subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject not found")));
    }

    public subjectDTO createSubject(subjectModel subject) {
        return subjectMapper.toDTO(subjectRepository.save(subject));
    }

    public subjectDTO updateSubject(subjectModel subject) {
        return subjectMapper.toDTO(subjectRepository.save(subject));
    }

    public subjectDTO deleteSubject(UUID id) {
        subjectRepository.deleteById(id);
        return null;
    }
}
