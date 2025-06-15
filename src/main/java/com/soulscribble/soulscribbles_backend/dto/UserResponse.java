package com.soulscribble.soulscribbles_backend.dto;

import java.time.LocalDate;

public class UserResponse {
    private String name;
    private String email;
    private String joinDate;

    public UserResponse(String name, String email, LocalDate joinDate) {
        this.name = name;
        this.email = email;
        this.joinDate = (joinDate != null) ? joinDate.toString() : "가입일 없음";
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getJoinDate() {
        return joinDate;
    }
}
