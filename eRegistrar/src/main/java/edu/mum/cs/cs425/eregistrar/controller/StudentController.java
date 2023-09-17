package edu.mum.cs.cs425.eregistrar.controller;

import edu.mum.cs.cs425.eregistrar.exception.StudentNotFoundException;
import edu.mum.cs.cs425.eregistrar.model.Student;
import edu.mum.cs.cs425.eregistrar.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = {"/student"})
    public String displayStudentPage() {
        return "student";
    }

    @GetMapping("/student/{id}")
    @ResponseBody
    public Student getStudent(@PathVariable long id) {
        return this.studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @GetMapping("/student/list")
    @ResponseBody
    public Iterable<Student> listStudents() {
        return this.studentRepository.findAll();
    }

    @PostMapping(value = "/student")
    @ResponseBody
    public Student createStudent(@RequestBody Student student) {
        return this.studentRepository.save(student);
    }

    @PutMapping(value = "/student/{id}")
    @ResponseBody
    public Student updateStudent(@PathVariable long id, @RequestBody Student newStudent) {
        return this.studentRepository.findById(id)
                .map(student -> {
                    student.setStudentNumber(newStudent.getStudentNumber());
                    student.setFirstName(newStudent.getFirstName());
                    student.setLastName(newStudent.getLastName());
                    student.setMiddleName(newStudent.getMiddleName());
                    student.setCgpa(newStudent.getCgpa());
                    student.setDateOfEnrollment(newStudent.getDateOfEnrollment());
                    student.setIsInternational(newStudent.getIsInternational());

                    return this.studentRepository.save(student);
                })
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @DeleteMapping("/student/{id}")
    @ResponseBody
    public boolean deleteEmployee(@PathVariable long id) {
        this.studentRepository.deleteById(id);
        return true;
    }
}
