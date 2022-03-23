package com.roopyx.springbootcrudrestfullwebservices.controller;

import com.roopyx.springbootcrudrestfullwebservices.entity.User;
import com.roopyx.springbootcrudrestfullwebservices.exception.ResourceNotFoundException;
import com.roopyx.springbootcrudrestfullwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId) {
        return existingUser(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUSer(@RequestBody User user, @PathVariable("id") long userId) {
        User existingUser = existingUser(userId);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return this.userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId) {
        User existingUser = existingUser(userId);
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

    private User existingUser(long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

}
