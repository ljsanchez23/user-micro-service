package com.emazon.UserMicroservice.configuration;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.emazon.UserMicroservice.domain.api.IRoleServicePort;
import com.emazon.UserMicroservice.domain.api.IUserServicePort;
import com.emazon.UserMicroservice.domain.util.DefaultRoles;
import com.emazon.UserMicroservice.domain.util.DefaultUsers;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final IRoleServicePort roleServicePort;
    private final IUserServicePort userServicePort;
    private final IUserRepository userRepository;

    public DataInitializer(IRoleServicePort roleServicePort, IUserServicePort userServicePort, IUserRepository userRepository) {
        this.roleServicePort = roleServicePort;
        this.userServicePort = userServicePort;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        roleServicePort.saveRole(DefaultRoles.ADMIN);
        roleServicePort.saveRole(DefaultRoles.ASSISTANT);
        roleServicePort.saveRole(DefaultRoles.CLIENT);
        if(userRepository.findByEmail(DefaultUsers.ADMIN.getEmail()).isEmpty()){
            userServicePort.createUser(DefaultUsers.ADMIN);
        }
        if (userRepository.findByEmail(DefaultUsers.ASSISTANT.getEmail()).isEmpty()) {
            userServicePort.createUser(DefaultUsers.ASSISTANT);
        }
        if (userRepository.findByEmail(DefaultUsers.CLIENT.getEmail()).isEmpty()) {
            userServicePort.createUser(DefaultUsers.CLIENT);
        }

    }
}
