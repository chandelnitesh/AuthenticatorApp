package com.authenticator.application.model;

public enum ROLE {
    USER("user"),
    ADMIN("admin");

    private final String role;

    ROLE(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
