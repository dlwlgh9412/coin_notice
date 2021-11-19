package com.jjangchen.common.model;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
