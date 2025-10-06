package com.PJ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.PJ.dtos.RegisterRequest;
import com.PJ.model.User;
import com.PJ.repository.UserRepository;
import com.PJ.service.UserService;

@Controller
@RequestMapping("/auth")
public class RegisterController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userRepository,
                              UserService userService,
                              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // templates/register.html
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest req, RedirectAttributes redirectAttributes) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            redirectAttributes.addFlashAttribute("error", "❌ Username นี้มีอยู่แล้ว");
            return "redirect:/auth/register";
        }

        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            redirectAttributes.addFlashAttribute("error", "❌ Email นี้มีอยู่แล้ว");
            return "redirect:/auth/register";
        }

        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setFullName(req.getFullName());
        u.setEmail(req.getEmail());
        u.setTel(req.getTel()); // เพิ่มฟิลด์เบอร์โทร
        userService.registerUser(u);

        redirectAttributes.addFlashAttribute("message", "✅ สมัครสมาชิกสำเร็จ กรุณาเข้าสู่ระบบ");
        return "redirect:/auth/login";
    }
}