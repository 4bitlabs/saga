package br.com.cacadoresti.saga.sagh.exception.auth;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
