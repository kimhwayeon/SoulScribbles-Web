package com.soulscribble.soulscribbles_backend.controller;

// src/main/java/com/soulscribble/controller/HomeController.java

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // templates/home.html
    }
}
