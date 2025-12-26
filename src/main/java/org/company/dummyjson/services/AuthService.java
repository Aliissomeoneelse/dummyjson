package org.company.dummyjson.services;

import lombok.RequiredArgsConstructor;
import org.company.dummyjson.config.JwtUtil;
import org.company.dummyjson.dto.LoginRequest;
import org.company.dummyjson.dto.LoginResponse;
import org.company.dummyjson.dto.UserResponse;
import org.company.dummyjson.models.Users;
import org.company.dummyjson.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {

        Users users = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!users.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(users.getId(), users.getUsername());

        return new LoginResponse(
                users.getId(),
                users.getUsername(),
                users.getEmail(),
                users.getFirstName(),
                users.getLastName(),
                users.getGender(),
                users.getImage(),
                token
        );
    }

    public UserResponse me(String token) {

        String username = jwtUtil.extractUsername(token);

        Users users = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponse(
                users.getId(),
                users.getUsername(),
                users.getEmail(),
                users.getFirstName(),
                users.getLastName(),
                users.getGender(),
                users.getImage()
        );
    }

    public LoginResponse refresh(String token) {

        String username = jwtUtil.extractUsername(token);

        Users users = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newToken = jwtUtil.generateToken(users.getId(), users.getUsername());

        return new LoginResponse(
                users.getId(),
                users.getUsername(),
                users.getEmail(),
                users.getFirstName(),
                users.getLastName(),
                users.getGender(),
                users.getImage(),
                newToken
        );
    }

}