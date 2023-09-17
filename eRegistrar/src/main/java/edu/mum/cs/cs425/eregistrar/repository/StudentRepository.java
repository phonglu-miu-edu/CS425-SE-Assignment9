package edu.mum.cs.cs425.eregistrar.repository;

import edu.mum.cs.cs425.eregistrar.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByStudentNumber(String studentNumber);
    List<Student> findByStudentNumberLikeOrFirstNameLikeIgnoreCaseOrMiddleNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(String studentNumber, String firstName, String middleName, String lastName);
}

