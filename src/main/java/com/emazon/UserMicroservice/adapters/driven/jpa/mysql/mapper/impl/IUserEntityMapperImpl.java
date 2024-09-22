package com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper.impl;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.emazon.UserMicroservice.domain.model.Role;
import com.emazon.UserMicroservice.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class IUserEntityMapperImpl implements IUserEntityMapper {
    @Override
    public UserEntity toEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.getId() );
        userEntity.setName( user.getName() );
        userEntity.setLastName( user.getLastName() );
        userEntity.setIdDocument( user.getIdDocument() );
        userEntity.setPhone( user.getPhone() );
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole( roleToRoleEntity( user.getRole() ) );

        return userEntity;
    }

    @Override
    public User toDomain(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }
        Long id = null;
        String name = null;
        String lastName = null;
        Integer idDocument = 0;
        String phone = null;
        LocalDate dateOfBirth = null;
        String email = null;
        String password = null;
        Role role = null;

        id = userEntity.getId();
        name = userEntity.getName();
        lastName = userEntity.getLastName();
        idDocument = userEntity.getIdDocument();
        phone = userEntity.getPhone();
        dateOfBirth = userEntity.getDateOfBirth();
        email = userEntity.getEmail();
        password = userEntity.getPassword();
        role = roleEntityToRol(userEntity.getRole());

        return new User(id, name, lastName, idDocument, phone, dateOfBirth,email , password, role);
    }

    protected RoleEntity roleToRoleEntity(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( role.getId() );
        roleEntity.setName( role.getName() );
        roleEntity.setDescription( role.getDescription() );

        return roleEntity;
    }

    protected Role roleEntityToRol(RoleEntity roleEntity) {
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
