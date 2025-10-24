package com.example.auth_api.services;

import com.example.auth_api.dtos.RegisterRequestDTO;
import com.example.auth_api.dtos.RegisterResponseDTO;
import com.example.auth_api.infra.security.TokenService;
import com.example.auth_api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should login successfully when everything is OK")
    void loginCase1() {

    }

    @Test
    @DisplayName("Should throw UserNotFoundException when the email is not registered")
    void loginCase2() {

    }

    @Test
    @DisplayName("Should throw PasswordNotMatchException when the password is incorrect")
    void loginCase3() {

    }

    @Test
    @DisplayName("Should register user with successfully!")
    void registerCase1() {
        // Arrange
        String username ="John";
        String email = "email@mail.com";
        String rawPassword = "John123";
        String encodedPassword = "encoded123";
        String fakeToken = "token.jwt.token";
        RegisterRequestDTO requestDTO = new RegisterRequestDTO(username, email, rawPassword);
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);
        when(tokenService.generateToken(email)).thenReturn(fakeToken);

        // Act
        RegisterResponseDTO result = this.authService.register(requestDTO);

        //Assert
        assertThat(result.token()).isEqualTo(fakeToken);
        verify(passwordEncoder).encode(rawPassword);
        verify(tokenService).generateToken(email);
    }

    @Test
    void registerCase2() {
    }

    @Test
    void registerCase3() {
    }
}