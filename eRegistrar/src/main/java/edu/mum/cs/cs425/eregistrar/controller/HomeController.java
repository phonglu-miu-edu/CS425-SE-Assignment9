package edu.mum.cs.cs425.eregistrar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "/eregistrar", "/eregistrar/home"})
    public String displayHomePage() {
        return "index";
    }
}

