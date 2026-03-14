package com.tomas.demo.features.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface subjectRepository extends JpaRepository<subjectModel, Integer> {
    
}
