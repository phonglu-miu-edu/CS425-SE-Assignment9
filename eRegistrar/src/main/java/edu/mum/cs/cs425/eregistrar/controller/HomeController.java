package edu.mum.cs.cs425.eregistrar.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping(value = {"/"})
    public String displayHomePage() {
        return "index";
    }
}

