package com.tomas.demo.features.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tomas.demo.features.student.studentModel;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface studentRepository extends JpaRepository<studentModel, UUID> {
    
    public Optional<studentModel> findByEmail(String email);
    public Optional<studentModel> findByDni(String dni);
    public Optional<studentModel> findByName(String name);
    public Optional<studentModel> findByFile(int file);
}
