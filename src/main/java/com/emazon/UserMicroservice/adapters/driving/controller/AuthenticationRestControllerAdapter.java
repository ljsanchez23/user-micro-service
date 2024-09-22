package com.emazon.UserMicroservice.adapters.driving.controller;

import com.emazon.UserMicroservice.adapters.driving.dto.response.AuthenticationResponse;
import com.emazon.UserMicroservice.adapters.driving.mapper.request.IAuthenticationRequestMapper;
import com.emazon.UserMicroservice.adapters.driving.mapper.response.IAuthenticationResponseMapper;
import com.emazon.UserMicroservice.adapters.util.AdapConstants;
import com.emazon.UserMicroservice.adapters.driving.dto.request.AuthenticationRequest;
import com.emazon.UserMicroservice.domain.api.IAuthenticationServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdapConstants.LOGIN_URL)
public class AuthenticationRestControllerAdapter {
    private final IAuthenticationServicePort authServicePort;
    private final IAuthenticationRequestMapper authenticationRequestMapper;
    private final IAuthenticationResponseMapper authenticationResponseMapper;

    public AuthenticationRestControllerAdapter(IAuthenticationServicePort authServicePort, IAuthenticationResponseMapper authenticationResponseMapper, IAuthenticationRequestMapper authenticationRequestMapper) {
        this.authServicePort = authServicePort;
        this.authenticationRequestMapper = authenticationRequestMapper;
        this.authenticationResponseMapper = authenticationResponseMapper;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse token = authenticationResponseMapper.toResponse(authServicePort.login(authenticationRequestMapper.toModel(authenticationRequest)));
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
