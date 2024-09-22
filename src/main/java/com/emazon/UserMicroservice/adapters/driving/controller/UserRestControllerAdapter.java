package com.emazon.UserMicroservice.adapters.driving.controller;

import com.emazon.UserMicroservice.adapters.driving.dto.request.UserRequest;
import com.emazon.UserMicroservice.adapters.driving.mapper.request.IUserRequestMapper;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(AdapConstants.USER_URL)
public class UserRestControllerAdapter {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    public UserRestControllerAdapter(IUserServicePort userServicePort, IUserRequestMapper userRequestMapper){
        this.userServicePort = userServicePort;
        this.userRequestMapper = userRequestMapper;
    }

    @Operation(summary = AdapConstants.CREATE_NEW_USER, description = AdapConstants.FOR_THE_SYSTEM)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdapConstants.RESPONSE_CODE_201, description = AdapConstants.USER_SUCCESSFULLY_CREATED,
                    content = @Content(mediaType = AdapConstants.APPLICATION_JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = AdapConstants.RESPONSE_CODE_400, description = AdapConstants.INVALID_USER,
                    content = @Content(mediaType = AdapConstants.APPLICATION_JSON, schema = @Schema(implementation = String.class)))
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
