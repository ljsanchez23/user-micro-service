package com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.UserMicroservice.domain.model.Role;

public interface IRoleEntityMapper {
    RoleEntity toEntity(Role role);
    Role toDomain(RoleEntity roleEntity);
}
