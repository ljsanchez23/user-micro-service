package com.emazon.UserMicroservice.domain.api.usecase;

import com.emazon.UserMicroservice.domain.api.IUserServicePort;
import com.emazon.UserMicroservice.domain.exception.UserAlreadyExistsException;
import com.emazon.UserMicroservice.domain.model.Role;
import com.emazon.UserMicroservice.domain.model.User;
import com.emazon.UserMicroservice.domain.spi.IPasswordEncoder;
import com.emazon.UserMicroservice.domain.spi.IUserPersistencePort;
import com.emazon.UserMicroservice.domain.util.Constants;
import com.emazon.UserMicroservice.domain.util.DefaultRoles;
import com.emazon.UserMicroservice.domain.util.Validator;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoder passwordEncoder;


    public UserUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoder passwordEncoder){
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(User user){
        if(userPersistencePort.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(Constants.USER_WITH + user.getEmail() + Constants.ALREADY_EXISTS);
        }
        if(user.getRole() == null){
            Role defaultRole = new Role(DefaultRoles.AUX_ID, DefaultRoles.AUX_ROLE, DefaultRoles.AUX_DESCRIPTION);
            user.setRole(defaultRole);
        }

        Validator.validateUser(user);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userPersistencePort.createUser(user);
    }
}
