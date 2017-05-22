package com.example.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by trainer2 on 5/17/17.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User postUser(@RequestBody User user){
        return this.userRepository.save(user);
    }

    @GetMapping
    public Iterable<User> all() {

        return this.userRepository.findAll();
    }
}
