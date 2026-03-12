package com.tomas.demo.features.student;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tomas.demo.features.student.studentModel;
import java.util.Optional;
public interface studentRepository extends JpaRepository<studentModel, Integer> {
    
    public Optional<studentModel> findByEmail(String email);
    public Optional<studentModel> findById(Long id);
    public Optional<studentModel> findByDni(String dni);
    public Optional<studentModel> findByName(String name);
    public Optional<studentModel> findByFile(int file);
}
