package com.emazon.UserMicroservice.adapters.driving.mapper.request;

import com.emazon.UserMicroservice.adapters.driving.dto.request.AuthenticationRequest;
import com.emazon.UserMicroservice.domain.model.Authentication;

public interface IAuthenticationRequestMapper {
    Authentication toModel(AuthenticationRequest auth);
}
