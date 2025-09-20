package br.com.cacadoresti.saga.sagh.exception.student;

public class StudentEnrollmentCodeNotFoundException extends RuntimeException {
    public StudentEnrollmentCodeNotFoundException(String message) {
        super(message);
    }
}
