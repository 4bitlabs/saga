package br.com.cacadoresti.saga.sagh.exception.user;

public class UserUUIDNotFoundException extends RuntimeException {
    public UserUUIDNotFoundException(String message) {
        super(message);
    }
}
