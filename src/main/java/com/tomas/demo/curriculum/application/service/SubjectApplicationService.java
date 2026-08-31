package com.tomas.demo.curriculum.application.service;

import com.tomas.demo.curriculum.application.dto.SubjectDTO;
import com.tomas.demo.curriculum.application.mapper.SubjectMapper;
import com.tomas.demo.curriculum.domain.model.Subject;
import com.tomas.demo.curriculum.domain.repository.SubjectRepository;
import com.tomas.demo.shared.domain.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SubjectApplicationService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectApplicationService(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Transactional(readOnly = true)
    public List<SubjectDTO> getAllSubjects() {
        return subjectMapper.toDtoList(subjectRepository.findAll());
    }

    @Transactional(readOnly = true)
    public SubjectDTO getSubjectById(UUID id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject", id));
        return subjectMapper.toDto(subject);
    }

    public SubjectDTO createSubject(SubjectDTO dto) {
        Subject subject = subjectMapper.toModel(dto);
        Subject saved = subjectRepository.save(subject);
        return subjectMapper.toDto(saved);
    }

    public SubjectDTO updateSubject(UUID id, SubjectDTO dto) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject", id));

        subject.setName(dto.name());
        subject.setCode(dto.code());
        subject.setDescription(dto.description());
        subject.setCredits(dto.credits());
        subject.setSemester(dto.semester());
        subject.setActive(dto.active());

        Subject updated = subjectRepository.save(subject);
        return subjectMapper.toDto(updated);
    }

    public void deleteSubject(UUID id) {
        if (!subjectRepository.existsById(id)) {
            throw new EntityNotFoundException("Subject", id);
        }
        subjectRepository.deleteById(id);
    }
}
