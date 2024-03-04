package com.grupo04pj01.urna.models.enums;

public enum Role {
    ADMIN("admin"),
    USER("user");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
