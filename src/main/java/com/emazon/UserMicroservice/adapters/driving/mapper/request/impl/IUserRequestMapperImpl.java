package com.emazon.UserMicroservice.adapters.driving.mapper.request.impl;

import com.emazon.UserMicroservice.adapters.driving.dto.request.UserRequest;
import com.emazon.UserMicroservice.adapters.driving.mapper.request.IUserRequestMapper;
import com.emazon.UserMicroservice.domain.model.Role;
import com.emazon.UserMicroservice.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class IUserRequestMapperImpl implements IUserRequestMapper {
    @Override
    public User toModel(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        String name = userRequest.getName();
        String lastName = userRequest.getLastName();
        Integer idDocument = userRequest.getIdDocument();
        String phone = userRequest.getPhone();
        LocalDate dateOfBirth = userRequest.getDateOfBirth();
        String email = userRequest.getEmail();
        String password = userRequest.getPassword();

        Role role = null;
        if (userRequest.getRole() != null) {
            role = new Role(
                    userRequest.getRole().getId(),
                    userRequest.getRole().getName(),
                    userRequest.getRole().getDescription()
            );
        }

        Long id = null;

        return new User( id, name, lastName, idDocument, phone, dateOfBirth, email, password, role );

    }
}
