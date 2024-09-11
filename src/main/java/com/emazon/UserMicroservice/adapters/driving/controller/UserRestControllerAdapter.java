package com.emazon.UserMicroservice.adapters.driving.controller;

import com.emazon.UserMicroservice.adapters.driving.dto.request.UserRequest;
import com.emazon.UserMicroservice.adapters.driving.mapper.IUserRequestMapper;
import com.emazon.UserMicroservice.adapters.util.AdapConstants;
import com.emazon.UserMicroservice.domain.api.IUserServicePort;
import com.emazon.UserMicroservice.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestControllerAdapter {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    public UserRestControllerAdapter(IUserServicePort userServicePort, IUserRequestMapper userRequestMapper){
        this.userServicePort = userServicePort;
        this.userRequestMapper = userRequestMapper;
    }

    @Operation(summary = "Create a new user", description = "Create a new user for the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user, please validate your params",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserRequest userRequest){
        User user = userRequestMapper.toModel(userRequest);
        userServicePort.createUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put(AdapConstants.MESSAGE, AdapConstants.USER_WITH + user.getEmail() + AdapConstants.HAS_BEEN_ADDED);
        response.put(AdapConstants.EMAIL, user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
