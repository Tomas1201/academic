package com.tomas.demo.features.career;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomas.demo.errors.ResourceNotFoundException;

@Service
public class careerService {

    @Autowired
    private careerRepository careerRepository;

    public careerDTO getCareer(int id) {
        return careerMapper.toDTO(careerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Career not found")));
    }

    public careerDTO createCareer(careerDTO career) {
        careerModel model = careerMapper.toModel(career);
        careerModel savedCareer = careerRepository.save(model);
        return careerMapper.toDTO(savedCareer);
    }

    public careerDTO updateCareer(careerDTO career) {
        careerModel model = careerMapper.toModel(career);
        careerModel savedCareer = careerRepository.save(model);
        return careerMapper.toDTO(savedCareer);
    }

    public careerDTO deleteCareer(int id) {
        careerRepository.deleteById(id);
        return null;
    }
}
