package com.example.auth_api.services;

import com.example.auth_api.domain.user.User;
import com.example.auth_api.dtos.LoginRequestDTO;
import com.example.auth_api.dtos.LoginResponseDTO;
import com.example.auth_api.dtos.RegisterRequestDTO;
import com.example.auth_api.dtos.RegisterResponseDTO;
import com.example.auth_api.exceptions.EmailAlreadyRegisteredException;
import com.example.auth_api.exceptions.PasswordNotMatchException;
import com.example.auth_api.exceptions.UserNotFoundException;
import com.example.auth_api.infra.security.TokenService;
import com.example.auth_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        User user = userRepository.findByEmail(requestDTO.email()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(requestDTO.password(), user.getPassword())) {
            throw new PasswordNotMatchException();
        }
        String token = this.tokenService.generateToken(user.getEmail());
        return new LoginResponseDTO(user.getName(), token);
    }

    public RegisterResponseDTO register(RegisterRequestDTO requestDTO) {
        Optional<User> user = this.userRepository.findByEmail(requestDTO.email());
        if(user.isPresent()) throw new EmailAlreadyRegisteredException();
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(requestDTO.password()));
        newUser.setEmail(requestDTO.email());
        newUser.setName(requestDTO.name());
        this.userRepository.save(newUser);

        String token = this.tokenService.generateToken(newUser.getEmail());
        return new RegisterResponseDTO(requestDTO.name(), token);
    }





}
