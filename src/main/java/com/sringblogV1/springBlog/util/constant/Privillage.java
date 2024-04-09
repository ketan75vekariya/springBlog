package com.sringblogv1.springblog.util.constant;

public enum Privillage {
    RESET_ANY_USER_PASSWORD(1l, "RESET_ANY_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2l, "ACCESS_ADMIN_PANEL");

    private Long Id;
    private String privillage;

    private Privillage(Long Id, String privillage) {
        this.Id = Id;
        this.privillage = privillage;
    }

    public Long getPrivillageId() {
        return Id;
    }

    public String getPrivillage() {
        return privillage;
    }
}