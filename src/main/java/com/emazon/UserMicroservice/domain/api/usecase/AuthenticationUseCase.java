package com.emazon.UserMicroservice.domain.api.usecase;

import com.emazon.UserMicroservice.domain.api.IAuthenticationServicePort;
import com.emazon.UserMicroservice.domain.exception.InvalidPasswordException;
import com.emazon.UserMicroservice.domain.exception.InvalidUsernameException;
import com.emazon.UserMicroservice.domain.model.Authentication;
import com.emazon.UserMicroservice.domain.model.User;
import com.emazon.UserMicroservice.domain.spi.ITokenPort;
import com.emazon.UserMicroservice.domain.spi.IEncoderPort;
import com.emazon.UserMicroservice.domain.spi.IUserPersistencePort;
import com.emazon.UserMicroservice.domain.util.Constants;
import com.emazon.UserMicroservice.domain.util.Validator;

public class AuthenticationUseCase implements IAuthenticationServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEncoderPort userPasswordEncoder;
    private final ITokenPort tokenPort;

    public AuthenticationUseCase(IUserPersistencePort userPersistencePort, IEncoderPort userPasswordEncoder, ITokenPort tokenPort){
        this.userPersistencePort = userPersistencePort;
        this.userPasswordEncoder = userPasswordEncoder;
        this.tokenPort = tokenPort;
    }

    @Override
    public Authentication login(Authentication authentication) {
        Validator.validateAuthentication(authentication);
        User validUser = userPersistencePort.findByEmail(authentication.getUsername());
        if (validUser == null) {
            throw new InvalidUsernameException(Constants.USER_NOT_FOUND);
        }
        boolean passwordMatches = userPasswordEncoder.matches(authentication.getPassword(), validUser.getPassword());
        if (!passwordMatches) {
            throw new InvalidPasswordException(Constants.INVALID_PASSWORD);
        }
        authentication.setPassword(null);
        authentication.setToken(tokenPort.getToken(validUser.getEmail()));
        return authentication;
    }

}
