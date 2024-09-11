package com.emazon.UserMicroservice.adapters.driven.jpa.mysql.adapter;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.UserMicroservice.domain.model.User;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

class UserAdapterTest {

    private IUserRepository userRepository;
    private IUserEntityMapper userEntityMapper;
    private UserAdapter userAdapter;

    @BeforeEach
    void setUp() {
        userRepository = mock(IUserRepository.class);
        userEntityMapper = mock(IUserEntityMapper.class);
        userAdapter = new UserAdapter(userRepository, userEntityMapper);
    }

    @Test
    @DisplayName("Should create the user")
    void shouldCreateUser() {
        User user = new User(1L, "John", "Doe", 1234567890, "1234567890", LocalDate.of(2000, 1, 1), "test@example.com", "password", null);

        when(userEntityMapper.toEntity(user)).thenReturn(new UserEntity());

        userAdapter.createUser(user);

        verify(userEntityMapper).toEntity(user);
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Should return true when the email already exists")
    void shouldReturnTrueWhenEmailExists() {
        String email = "test@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new UserEntity()));

        boolean exists = userAdapter.existsByEmail(email);

        assertTrue(exists);
    }

    @Test
    @DisplayName("Should return false when the email does not exists")
    void shouldReturnFalseWhenEmailDoesNotExist() {
        String email = "test@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        boolean exists = userAdapter.existsByEmail(email);

        assertFalse(exists);
    }
}
