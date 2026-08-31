package com.tomas.demo.curriculum.infrastructure.persistence;

import com.tomas.demo.curriculum.domain.model.Career;
import com.tomas.demo.curriculum.domain.repository.CareerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CareerJpaRepository extends JpaRepository<Career, UUID>, CareerRepository {
}
