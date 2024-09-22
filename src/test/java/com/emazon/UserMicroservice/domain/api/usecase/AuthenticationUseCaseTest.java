package com.emazon.UserMicroservice.domain.api.usecase;

import com.emazon.UserMicroservice.domain.exception.InvalidPasswordException;
import com.emazon.UserMicroservice.domain.exception.InvalidUsernameException;
import com.emazon.UserMicroservice.domain.model.Authentication;
import com.emazon.UserMicroservice.domain.model.User;
import com.emazon.UserMicroservice.domain.spi.ITokenPort;
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
class AuthenticationUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IEncoderPort userPasswordEncoder;

    @Mock
    private ITokenPort tokenPort;

    @InjectMocks
    private AuthenticationUseCase authUseCase;

    @Test
    @DisplayName("Login method with correct credentials")
    void shouldAccessWithCorrectCredentials() {
        Authentication authenticationRequest = new Authentication("testuser@example.com", "rawPassword", null);
        User mockUser = new User(1L, "John", "Doe", 12345678, "123-456-7890", LocalDate.of(1990, 1, 1), "testuser@example.com", "encodedPassword", DefaultRoles.CLIENT);

        String encodedPassword = "encodedPassword";

        when(userPersistencePort.findByEmail("testuser@example.com")).thenReturn(mockUser);
        when(userPasswordEncoder.matches("rawPassword", encodedPassword)).thenReturn(true);
        when(tokenPort.getToken("testuser@example.com")).thenReturn("validToken");

        Authentication result = authUseCase.login(authenticationRequest);

        assertNotNull(result);
        assertEquals("validToken", result.getToken());
        assertNull(result.getPassword());

        verify(userPersistencePort).findByEmail("testuser@example.com");
        verify(userPasswordEncoder).matches("rawPassword", encodedPassword);
        verify(tokenPort).getToken("testuser@example.com");
    }

    @Test
    @DisplayName("Login method when the user does not exist in the system")
    void shouldThrowExceptionWhenUserDoesNotExists() {
        Authentication authenticationRequest = new Authentication("admin@emazon.com", "admin", null);

        when(userPersistencePort.findByEmail("admin@emazon.com")).thenReturn(null);

        Exception exception = assertThrows(InvalidUsernameException.class, () -> {
            authUseCase.login(authenticationRequest);
        });
        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userPersistencePort).findByEmail("admin@emazon.com");
        verify(userPasswordEncoder, never()).matches(anyString(), anyString());
        verify(tokenPort, never()).getToken(anyString());
    }

    @Test
    @DisplayName("Login method when the password is incorrect")
    void shouldThrowExceptionWhenPasswordIsIncorrect() {
        Authentication authenticationRequest = new Authentication("testuser@example.com", "wrongPassword", null);
        User mockUser = new User(1L, "John", "Doe", 12345678, "123-456-7890", LocalDate.of(1990, 1, 1), "testuser@example.com", "encodedPassword", DefaultRoles.CLIENT);

        String encodedPassword = "encodedPassword";

        when(userPersistencePort.findByEmail("testuser@example.com")).thenReturn(mockUser);
        when(userPasswordEncoder.matches("wrongPassword", encodedPassword)).thenReturn(false);
        Exception exception = assertThrows(InvalidPasswordException.class, () -> {
            authUseCase.login(authenticationRequest);
        });
        String expectedMessage = Constants.INVALID_PASSWORD;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userPersistencePort).findByEmail("testuser@example.com");
        verify(userPasswordEncoder).matches("wrongPassword", encodedPassword);
        verify(tokenPort, never()).getToken(anyString());
    }
}
