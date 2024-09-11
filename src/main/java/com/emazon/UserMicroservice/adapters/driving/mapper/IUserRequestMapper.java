package com.emazon.UserMicroservice.adapters.driving.mapper;

import com.emazon.UserMicroservice.adapters.driving.dto.request.UserRequest;
import com.emazon.UserMicroservice.domain.model.User;

public interface IUserRequestMapper {
    User toModel(UserRequest userRequest);
}
