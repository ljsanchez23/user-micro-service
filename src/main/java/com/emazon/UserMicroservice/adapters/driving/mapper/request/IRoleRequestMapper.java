package com.emazon.UserMicroservice.adapters.driving.mapper.request;

import com.emazon.UserMicroservice.adapters.driving.dto.request.RoleRequest;
import com.emazon.UserMicroservice.domain.model.Role;

public interface IRoleRequestMapper {
    Role toModel(RoleRequest roleRequest);
}
