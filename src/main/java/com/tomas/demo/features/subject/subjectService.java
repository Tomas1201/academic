package com.tomas.demo.features.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import errors.ResourceNotFoundException;

@Service
public class subjectService {

    @Autowired
    private subjectRepository subjectRepository;

    public subjectDTO getSubject(int id) {
        return subjectMapper.toDTO(subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject not found")));
    }

    public subjectDTO createSubject(subjectModel subject) {
        return subjectMapper.toDTO(subjectRepository.save(subject));
    }

    public subjectDTO updateSubject(subjectModel subject) {
        return subjectMapper.toDTO(subjectRepository.save(subject));
    }

    public subjectDTO deleteSubject(int id) {
        subjectRepository.deleteById(id);
        return null;
    }
}
