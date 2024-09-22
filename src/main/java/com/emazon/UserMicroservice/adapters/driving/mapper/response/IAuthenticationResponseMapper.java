package com.emazon.UserMicroservice.adapters.driving.mapper.response;

import com.emazon.UserMicroservice.adapters.driving.dto.response.AuthenticationResponse;
import com.emazon.UserMicroservice.domain.model.Authentication;

public interface IAuthenticationResponseMapper {
    AuthenticationResponse toResponse(Authentication authentication);
}
