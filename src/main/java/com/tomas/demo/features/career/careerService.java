package com.tomas.demo.features.career;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import errors.ResourceNotFoundException;

@Service
public class careerService {

    @Autowired
    private careerRepository careerRepository;

    public careerDTO getCareer(int id) {
        return careerMapper.toDTO(careerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Career not found")));
    }

    public careerDTO createCareer(careerModel career) {
        return careerMapper.toDTO(careerRepository.save(career));
    }

    public careerDTO updateCareer(careerModel career) {
        return careerMapper.toDTO(careerRepository.save(career));
    }

    public careerDTO deleteCareer(int id) {
        careerRepository.deleteById(id);
        return null;
    }
}
