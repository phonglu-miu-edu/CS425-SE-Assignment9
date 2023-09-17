package edu.mum.cs.cs425.eregistrar.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(long id) {
        super("Could not find student " + id);
    }
}