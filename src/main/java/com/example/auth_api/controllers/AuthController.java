package com.example.auth_api.controllers;

import com.example.auth_api.dtos.LoginRequestDTO;
import com.example.auth_api.dtos.LoginResponseDTO;
import com.example.auth_api.dtos.RegisterRequestDTO;
import com.example.auth_api.dtos.RegisterResponseDTO;
import com.example.auth_api.infra.security.TokenService;
import com.example.auth_api.repositories.UserRepository;
import com.example.auth_api.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO body) {
        LoginResponseDTO response = authService.login(body);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        RegisterResponseDTO response = authService.register(body);
        return ResponseEntity.ok(response);
    }


}
