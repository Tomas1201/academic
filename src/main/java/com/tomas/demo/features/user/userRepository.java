package com.tomas.demo.features.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tomas.demo.features.user.userModel;
import java.util.Optional;
public interface userRepository extends JpaRepository<userModel, Integer> {
    
    
    public Optional<userModel> create(userModel user);
    public Optional<userModel> findByEmail(String email);
    public Optional<userModel> findById(Long id);
    public Optional<userModel> findByDni(String dni);
    public Optional<userModel> findByName(String name);
    public Optional<userModel> findByFile(int file);
}
