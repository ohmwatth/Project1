package com.PJ.controller;

import com.PJ.dtos.RegisterRequest;
import com.PJ.dtos.RegisterResponse;
import com.PJ.model.User;
import com.PJ.repository.UserRepository;
import com.PJ.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class RegisterRestController {

    private final UserService userService;
    private final UserRepository userRepository;

    public RegisterRestController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest req) {

        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            return new RegisterResponse(false, "Username already exists!");
        }
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            return new RegisterResponse(false, "Email already exists!");
        }

        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setFullName(req.getFullName());
        u.setPassword(req.getPassword());

        userService.registerUser(u); // ✅ encode password + save

        return new RegisterResponse(true, "Register success!");
    }
}