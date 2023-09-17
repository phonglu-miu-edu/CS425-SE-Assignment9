package edu.mum.cs.cs425.eregistrar.controller;

import edu.mum.cs.cs425.eregistrar.exception.StudentExistsException;
import edu.mum.cs.cs425.eregistrar.exception.StudentNotFoundException;
import edu.mum.cs.cs425.eregistrar.model.Student;
import edu.mum.cs.cs425.eregistrar.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Iterable<Student> listStudents(@RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return this.studentRepository.findAll();
        } else {
            String searchKeyword = '%' + keyword + '%';
            return this.studentRepository.findByStudentNumberLikeOrFirstNameLikeIgnoreCaseOrMiddleNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(searchKeyword, searchKeyword, searchKeyword, searchKeyword);
        }
    }

    @PostMapping(value = "/student")
    @ResponseBody
    public Student createStudent(@RequestBody Student student) {
        String studentNumber = student.getStudentNumber();
        Student newStudent = this.studentRepository.findByStudentNumber(studentNumber);
        if (newStudent != null) {
            throw new StudentExistsException(studentNumber);
        }

        return this.studentRepository.save(student);
    }

    @PutMapping(value = "/student/{id}")
    @ResponseBody
    public Student updateStudent(@PathVariable long id, @RequestBody Student newStudent) {
        return this.studentRepository.findById(id)
                .map(student -> {
                    String studentNumber = newStudent.getStudentNumber();
                    Student oldStudent = this.studentRepository.findByStudentNumber(studentNumber);
                    if (oldStudent != null && oldStudent.getStudentId() != id) {
                        throw new StudentExistsException(studentNumber);
                    }

                    student.setStudentNumber(studentNumber);
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
    public boolean deleteStudent(@PathVariable long id) {
        this.studentRepository.deleteById(id);
        return true;
    }
}
