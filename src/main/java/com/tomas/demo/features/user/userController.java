package com.tomas.demo.features.user;

import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/users")
public class userController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable int id) {
        
        return ResponseEntity.status(201).body("pepe");
    }

}
