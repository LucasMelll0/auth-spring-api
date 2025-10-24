package com.example.auth_api.repositories;

import com.example.auth_api.domain.user.User;
import com.example.auth_api.dtos.RegisterRequestDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Should get user by email from DB!")
    void findByEmailSuccessfully() {
        RegisterRequestDTO dto = new RegisterRequestDTO("Lucas", "email@email.com", "senha123");
        User data = this.createUser(dto);
        Optional<User> result = this.userRepository.findByEmail(data.getEmail());
        assertThat(result.isPresent()).isTrue();
    }

    private User createUser(RegisterRequestDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        entityManager.persist(user);
        return user;
    }
}