package org.company.dummyjson.controllers;

import lombok.RequiredArgsConstructor;
import org.company.dummyjson.dto.LoginRequest;
import org.company.dummyjson.dto.LoginResponse;
import org.company.dummyjson.dto.UserResponse;
import org.company.dummyjson.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public UserResponse me(
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.replace("Bearer ", "");
        return authService.me(token);
    }

    @PostMapping("/refresh")
    public LoginResponse refresh(
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.replace("Bearer ", "");
        return authService.refresh(token);
    }

}