package com.PJ.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.PJ.model.Subject;
import com.PJ.model.User;
import com.PJ.repository.SubjectRepository;
import com.PJ.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectRepository subjectRepo;
    private final UserRepository userRepo;

    public SubjectController(SubjectRepository subjectRepo, UserRepository userRepo) {
        this.subjectRepo = subjectRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String listSubjects(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepo.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Subject> subjects = subjectRepo.findByUser(user);
        model.addAttribute("subjects", subjects);
        return "subjects"; // templates/subjects.html
    }

    @PostMapping("/add")
    public String addSubject(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestParam String name) {
        User user = userRepo.findByUsername(userDetails.getUsername()).orElseThrow();
        Subject subject = new Subject();
        subject.setName(name);
        subject.setUser(user);
        subjectRepo.save(subject);
        return "redirect:/subjects";
    }
}