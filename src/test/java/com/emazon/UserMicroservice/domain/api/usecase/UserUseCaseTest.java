package com.emazon.UserMicroservice.domain.api.usecase;

import com.emazon.UserMicroservice.domain.exception.UserAlreadyExistsException;
import com.emazon.UserMicroservice.domain.model.Role;
import com.emazon.UserMicroservice.domain.model.User;
import com.emazon.UserMicroservice.domain.spi.IEncoderPort;
import com.emazon.UserMicroservice.domain.spi.IUserPersistencePort;
import com.emazon.UserMicroservice.domain.util.Constants;
import com.emazon.UserMicroservice.domain.util.DefaultRoles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IEncoderPort passwordEncoder;

    @InjectMocks
    private UserUseCase userUseCase;


    @Test
    @DisplayName("Should throw exception when user email already exists")
    void shouldThrowExceptionWhenUserEmailAlreadyExists() {
        User user = new User(1L, "Pruebino", "Proboso", 1111111111, "3222222", LocalDate.of(1998, 12, 23), "test@example.com", "password", null);
        user.setEmail("test@example.com");

        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(true);

        UserAlreadyExistsException thrown =
                assertThrows(UserAlreadyExistsException.class, () -> userUseCase.createUser(user));

        assertEquals(Constants.USER_WITH + user.getEmail() + Constants.ALREADY_EXISTS, thrown.getMessage());
    }

    @Test
    @DisplayName("Should set default role when no role is provided")
    void shouldSetDefaultRoleWhenNoRoleIsProvided() {
        User user = new User(1L, "Pruebino", "Proboso", 1111111111, "3222222", LocalDate.of(1998, 12, 23), "test@example.com", "password", null);
        user.setEmail("test@example.com");
        user.setRole(null);
        user.setPassword("encryptedPassword");

        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encryptedPassword");

        userUseCase.createUser(user);

        verify(userPersistencePort).createUser(user);
        assertNotNull(user.getRole());
        assertEquals(DefaultRoles.ASSISTANT_ID, user.getRole().getId());
        assertEquals(DefaultRoles.ASSISTANT_ROLE_NAME, user.getRole().getName());
        assertEquals(DefaultRoles.ASSISTANT_DESCRIPTION, user.getRole().getDescription());
    }

    @Test
    @DisplayName("Should encrypt user password before saving")
    void shouldEncryptUserPasswordBeforeSaving() {
        User user = new User(1L, "Pruebino", "Proboso", 1111111111, "3222222", LocalDate.of(1998, 12, 23), "test@example.com", "encryptedPassword", null);
        user.setEmail("test@example.com");
        user.setRole(new Role(1L, "Warehouse assistant", "Warehouse assistant who assist"));
        user.setPassword("encryptedPassword");

        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encryptedPassword");

        userUseCase.createUser(user);

        verify(userPersistencePort).createUser(user);
        verify(passwordEncoder).encode(user.getPassword());
        assertEquals("encryptedPassword", user.getPassword());
    }
}
