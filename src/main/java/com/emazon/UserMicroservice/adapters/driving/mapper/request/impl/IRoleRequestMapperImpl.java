package com.emazon.UserMicroservice.adapters.driving.mapper.request.impl;

import com.emazon.UserMicroservice.adapters.driving.dto.request.RoleRequest;
import com.emazon.UserMicroservice.adapters.driving.mapper.request.IRoleRequestMapper;
import com.emazon.UserMicroservice.domain.model.Role;
import org.springframework.stereotype.Component;

@Component
public class IRoleRequestMapperImpl implements IRoleRequestMapper {
    @Override
    public Role toModel(RoleRequest roleRequest) {
        if (roleRequest == null) {
            return null;
        }
        Long id = roleRequest.getId();
        String name = roleRequest.getName();
        String description = roleRequest.getDescription();

        return new Role(id, name, description);
    }
}
