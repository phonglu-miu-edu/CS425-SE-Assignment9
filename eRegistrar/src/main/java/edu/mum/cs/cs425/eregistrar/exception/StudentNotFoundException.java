package edu.mum.cs.cs425.eregistrar.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(long id) {
        super(String.format("Could not find student %d", id));
    }
}

