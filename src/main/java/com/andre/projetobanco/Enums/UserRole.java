package com.andre.projetobanco.Enums;

public enum UserRole {
    CLIENT("client"),
    EMPLOYEE("employee");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
