package com.emazon.UserMicroservice.configuration;

import com.emazon.UserMicroservice.domain.api.IRoleServicePort;
import com.emazon.UserMicroservice.domain.model.Role;
import com.emazon.UserMicroservice.domain.util.DefaultRoles;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final IRoleServicePort roleServicePort;

    public DataInitializer(IRoleServicePort roleServicePort) {
        this.roleServicePort = roleServicePort;
    }

    @PostConstruct
    public void init() {
        roleServicePort.saveRole(new Role(DefaultRoles.AUX_ID, DefaultRoles.AUX_ROLE, DefaultRoles.AUX_DESCRIPTION));
    }
}
