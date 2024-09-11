package com.emazon.UserMicroservice.domain.api.usecase;

import com.emazon.UserMicroservice.domain.api.IRoleServicePort;
import com.emazon.UserMicroservice.domain.model.Role;
import com.emazon.UserMicroservice.domain.spi.IRolePersistencePort;

public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort){
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void saveRole(Role role){
        rolePersistencePort.saveRole(role);
    }
}
