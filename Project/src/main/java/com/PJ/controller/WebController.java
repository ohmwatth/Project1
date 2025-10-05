package com.PJ.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html
    }

    
}