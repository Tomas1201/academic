package com.tomas.demo.features.career;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tomas.demo.features.career.careerModel;
import java.util.Optional;

@Repository
public interface careerRepository extends JpaRepository<careerModel, Integer> {

    public Optional<careerModel> findByCode(String code);

    public Optional<careerModel> findById(int id);

    public Optional<careerModel> findByName(String name);
}
