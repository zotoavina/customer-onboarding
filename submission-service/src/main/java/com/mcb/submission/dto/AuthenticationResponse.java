package com.mcb.submission.dto;

import com.mcb.submission.persistence.entity.Manager;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private String role;

    public static AuthenticationResponse fromUser(Manager boManager, String token) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setEmail(boManager.getEmail());
        authenticationResponse.setFirstName(boManager.getFirstName());
        authenticationResponse.setLastName(boManager.getLastName());
        authenticationResponse.setToken(token);
        authenticationResponse.setRole(boManager.getProfile().getProfileCode());
        return authenticationResponse;
    }
}
