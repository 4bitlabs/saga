package br.com.cacadoresti.saga.sagh.exception.course;

public class CourseUUIDNotFoundException extends RuntimeException {
    public CourseUUIDNotFoundException(String message) {
        super(message);
    }
}
