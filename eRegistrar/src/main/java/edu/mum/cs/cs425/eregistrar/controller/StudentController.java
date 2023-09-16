package edu.mum.cs.cs425.eregistrar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    @GetMapping(value = {"/student", "/eregistrar/student"})
    public String getList() {
        return "student";
    }
}
