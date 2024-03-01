package com.project.fullstack.controller;

import com.project.fullstack.model.User;
import com.project.fullstack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/user")
    User addUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
    @GetMapping("/users")
    List<User> getAlluser(){
        return userRepository.findAll();
    }
    @GetMapping("/users/{id}")
    Optional<User> getByid(@PathVariable Long id){
        return userRepository.findById(id);
    }
    @PutMapping("/users/{id}")
    Optional<User> uppdateUSer(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id).map(user -> {
            user.setEmail(newUser.getEmail());
            user.setName(newUser.getName());
            user.setUsername(newUser.getUsername());
            return userRepository.save(user);
        });
    }
    @DeleteMapping("users/{id}")
    String deleteByid(@PathVariable Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "user with id "+ id +" has been deleted";
        }
        return "user not found";
    }

}
