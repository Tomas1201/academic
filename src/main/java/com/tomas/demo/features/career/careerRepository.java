package com.tomas.demo.features.career;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tomas.demo.features.career.careerModel;
import java.util.Optional;

@Repository
public interface careerRepository extends JpaRepository<careerModel, Integer> {
    @Query("SELECT c FROM careerModel c WHERE c.id = :id")
    public Optional<careerModel> findByPk(int id);

    public Optional<careerModel> findAllByActiveIsTrue();
    public Optional<careerModel> findByCode(String code);
    public Optional<careerModel> findByName(String name);
}
