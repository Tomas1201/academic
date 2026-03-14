package com.tomas.demo.features.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface teacherRepository extends JpaRepository<teacherModel, Integer> {
    
}
