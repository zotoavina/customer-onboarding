package com.mcb.submission.controller;

import com.mcb.submission.dto.AuthenticationRequest;
import com.mcb.submission.dto.AuthenticationResponse;
import com.mcb.submission.dto.ResponseFormatDto;
import com.mcb.submission.persistence.entity.Manager;
import com.mcb.submission.service.impl.ManagerServiceImpl;
import com.mcb.submission.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/submission/manager")
public class ManagerController {

    private final AuthenticationManager authenticationManager;

    private final ManagerServiceImpl managerService;

    public ManagerController(AuthenticationManager manager,
                             ManagerServiceImpl managerService) {
        authenticationManager = manager;
        this.managerService = managerService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseFormatDto> login(@RequestBody AuthenticationRequest authentication) {
        log.info("AUTHENTICATION: Try to authenticate user : {}", authentication.getEmail());
        HttpStatus status = HttpStatus.OK;
        AuthenticationResponse response = null;
        String message = "Authenticated with success";
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authentication.getEmail(),
                    authentication.getPassword()
            ));
            log.info("AUTHENTICATION : Load user from database : {}", authentication.getEmail());
            Manager boManager = managerService.loadUserByUsername(authentication.getEmail());
            log.info("AUTHENTICATION : generate token for user : {}", authentication.getEmail());
            String token = JwtTokenUtil.generateToken(boManager.getEmail(), null);
            response = AuthenticationResponse.fromUser(boManager, token);
        } catch (AuthenticationException e) {
            log.warn("AUTHENTICATION : bad credentials for user {}, error {}", authentication.getEmail(), e.getMessage());
            message = "Email or password invalid";
            status = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            log.error("AUTHENTICATION : an unknown error occurred when trying to authenticate user {}, error : {}",
                    authentication.getEmail(), e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "unknown error occurred during authentication process";
        }
        return ResponseFormatDto.buildResponse(response, status, message);
    }
}
