package edu.mum.cs.cs425.eregistrar.exception;

public class StudentExistsException extends RuntimeException {
    public StudentExistsException(String studentNumber) {
        super(String.format("Student number %s existed", studentNumber));
    }
}
