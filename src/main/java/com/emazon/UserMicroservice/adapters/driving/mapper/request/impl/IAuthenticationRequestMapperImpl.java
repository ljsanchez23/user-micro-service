package com.emazon.UserMicroservice.adapters.driving.mapper.request.impl;

import com.emazon.UserMicroservice.adapters.driving.dto.request.AuthenticationRequest;
import com.emazon.UserMicroservice.adapters.driving.mapper.request.IAuthenticationRequestMapper;
import com.emazon.UserMicroservice.domain.model.Authentication;
import org.springframework.stereotype.Component;

@Component
public class IAuthenticationRequestMapperImpl implements IAuthenticationRequestMapper {
    @Override
    public Authentication toModel(AuthenticationRequest auth) {
        if(auth == null) {
            return null;
        }

        String username = auth.getUsername();
        String password = auth.getPassword();
        String token = null;

        return new Authentication(username, password, token);
    }
}
