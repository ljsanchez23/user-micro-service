package com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper.impl;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.emazon.UserMicroservice.domain.model.Role;
import org.springframework.stereotype.Component;

@Component
public class IRoleEntityMapperImpl implements IRoleEntityMapper {
    @Override
    public RoleEntity toEntity(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( role.getId() );
        roleEntity.setName( role.getName() );
        roleEntity.setDescription( role.getDescription() );

        return roleEntity;
    }

    @Override
    public Role toDomain(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        String name = null;
        String description = null;
        Long id = null;

        name = roleEntity.getName();
        description = roleEntity.getDescription();
        id = roleEntity.getId();

        return new Role( id, name, description );
    }
}
