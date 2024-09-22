package com.emazon.UserMicroservice.domain.api.usecase;

import com.emazon.UserMicroservice.domain.api.IUserServicePort;
import com.emazon.UserMicroservice.domain.exception.UserAlreadyExistsException;
import com.emazon.UserMicroservice.domain.model.User;
import com.emazon.UserMicroservice.domain.spi.IEncoderPort;
import com.emazon.UserMicroservice.domain.spi.IUserPersistencePort;
import com.emazon.UserMicroservice.domain.util.Constants;
import com.emazon.UserMicroservice.domain.util.DefaultRoles;
import com.emazon.UserMicroservice.domain.util.Validator;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IEncoderPort passwordEncoder;


    public UserUseCase(IUserPersistencePort userPersistencePort, IEncoderPort passwordEncoder){
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(User user){
        if(userPersistencePort.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(Constants.USER_WITH + user.getEmail() + Constants.ALREADY_EXISTS);
        }
        if(user.getRole() == null){
            user.setRole(DefaultRoles.ASSISTANT);
        }

        Validator.validateUser(user);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userPersistencePort.createUser(user);
    }

}
