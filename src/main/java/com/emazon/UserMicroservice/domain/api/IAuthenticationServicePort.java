package com.emazon.UserMicroservice.domain.api;

import com.emazon.UserMicroservice.domain.model.Authentication;

public interface IAuthenticationServicePort {
    Authentication login(Authentication authentication);
}
