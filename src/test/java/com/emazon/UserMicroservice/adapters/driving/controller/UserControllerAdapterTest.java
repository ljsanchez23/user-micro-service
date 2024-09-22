package com.emazon.UserMicroservice.adapters.driving.controller;

import com.emazon.UserMicroservice.adapters.driving.dto.request.UserRequest;
import com.emazon.UserMicroservice.adapters.driving.mapper.request.IUserRequestMapper;
import com.emazon.UserMicroservice.adapters.util.AdapConstants;
import com.emazon.UserMicroservice.domain.api.IUserServicePort;
import com.emazon.UserMicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerAdapterTest {
    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IUserRequestMapper userRequestMapper;

    @InjectMocks
    private UserRestControllerAdapter userRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userRestControllerAdapter = new UserRestControllerAdapter(userServicePort, userRequestMapper);
    }

    @Test
    @DisplayName("Should create a new user correctly")
    void shouldSaveCategory() {
        UserRequest userRequest = new UserRequest(1L, "Pruebino", "Proboso", 1111111111, "3222222", LocalDate.of(1998, 12, 23), "test@example.com", "encryptedPassword", null);
        User user = new User(1L, "Pruebino", "Proboso", 1111111111, "3222222", LocalDate.of(1998, 12, 23), "test@example.com", "encryptedPassword", null);
        when(userRequestMapper.toModel(userRequest)).thenReturn(user);

        ResponseEntity<Map<String, Object>> response = userRestControllerAdapter.createUser(userRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(AdapConstants.USER_WITH + user.getEmail() + AdapConstants.HAS_BEEN_ADDED, response.getBody().get("message"));
        verify(userServicePort, times(1)).createUser(user);
    }

}
