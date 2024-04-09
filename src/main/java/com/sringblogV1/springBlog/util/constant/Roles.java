package com.sringblogv1.springblog.util.constant;

public enum Roles {
  USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    EDITOR("ROLE_EDITOR");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
