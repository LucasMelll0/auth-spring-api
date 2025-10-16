package com.example.auth_api.controllers;

import com.example.auth_api.domain.user.User;
import com.example.auth_api.dto.GetLoggedUserResponseDTO;
import com.example.auth_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;


    @GetMapping
    public ResponseEntity<GetLoggedUserResponseDTO> getUser(@AuthenticationPrincipal User loggedUser) {
        final GetLoggedUserResponseDTO response = new GetLoggedUserResponseDTO(loggedUser.getName(), loggedUser.getEmail());
        return ResponseEntity.ok(response);
    }

}
