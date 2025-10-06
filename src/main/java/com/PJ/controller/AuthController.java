package com.PJ.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.PJ.config.JwtUtil;
import com.PJ.dtos.AuthRequest;
import com.PJ.dtos.AuthResponse;
import com.PJ.model.LoginAudit;
import com.PJ.model.User;
import com.PJ.repository.LoginAuditRepository;
import com.PJ.repository.UserRepository;
import com.PJ.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final LoginAuditRepository auditRepo;
    private final UserRepository userRepo;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil,
                          LoginAuditRepository auditRepo, UserRepository userRepo) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.auditRepo = auditRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "❌ Username หรือ Password ไม่ถูกต้อง");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute AuthRequest req, HttpServletRequest httpReq, Model model) {
        LoginAudit audit = new LoginAudit();
        audit.setUsername(req.getUsername());
        audit.setLoginAt(LocalDateTime.now());
        audit.setIpAddress(httpReq.getRemoteAddr());

        User user = userRepo.findByUsername(req.getUsername()).orElse(null);
        if (user == null) {
            model.addAttribute("error", "❌ ยังไม่ได้สมัครสมาชิก กรุณาสมัครก่อน");
            return "login";
        }

        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );
            audit.setSuccess(true);
            auditRepo.save(audit);
            return "redirect:/tasks";
        } catch (BadCredentialsException ex) {
            audit.setSuccess(false);
            audit.setFailureReason("Bad credentials");
            auditRepo.save(audit);
            model.addAttribute("error", "❌ Username หรือ Password ไม่ถูกต้อง");
            return "login";
        }
    }
}
