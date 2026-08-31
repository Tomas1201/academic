package com.tomas.demo.student.infrastructure.persistence;

import com.tomas.demo.student.domain.model.Student;
import com.tomas.demo.student.domain.repository.StudentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, UUID>, StudentRepository {
    @Override
    Optional<Student> findByDni(String dni);

    @Override
    Optional<Student> findByEmail(String email);
}
