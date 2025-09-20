package br.com.cacadoresti.saga.sagh.model.enums;

import lombok.Getter;

@Getter
public enum AppRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    AppRole(String role) {
        this.role = role;
    }
}
