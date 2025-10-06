package com.PJ.controller;

import com.PJ.model.User;
import com.PJ.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/edit")
    public String showEditProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User u = userRepository.findByUsername(userDetails.getUsername())
                               .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", u);
        return "edit";
    }

    @PostMapping("/user/edit")
    public String editProfile(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam String email,
                              @RequestParam String fullName,
                              Model model) {
    	User u = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        u.setEmail(email);
        u.setFullName(fullName);
        userRepository.save(u);
        model.addAttribute("message", "Profile updated successfully!");
        model.addAttribute("user", u);
        return "edit";
    }
}
