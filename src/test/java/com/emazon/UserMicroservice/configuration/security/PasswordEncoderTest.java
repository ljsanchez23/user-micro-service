package com.emazon.UserMicroservice.configuration.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class PasswordEncoderTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should encode password using BCryptPasswordEncoder")
    void shouldEncodePassword() {
        String rawPassword = "mySecretPassword";
        String encodedPassword = "encodedPassword";

        when(bCryptPasswordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        String result = passwordEncoder.encode(rawPassword);
        verify(bCryptPasswordEncoder).encode(rawPassword);

        assertEquals(encodedPassword, result);
    }
}
