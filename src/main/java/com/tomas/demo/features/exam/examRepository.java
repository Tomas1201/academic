package com.tomas.demo.features.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface examRepository extends JpaRepository<examModel, UUID> {
    List<examModel> findByStudentId(UUID studentId);
    List<examModel> findBySubjectId(UUID subjectId);
}
