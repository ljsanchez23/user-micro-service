package com.emazon.UserMicroservice.adapters.driven.jpa.mysql.adapter;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.emazon.UserMicroservice.domain.model.Role;
import com.emazon.UserMicroservice.domain.spi.IRolePersistencePort;

public class RoleAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    public RoleAdapter(IRoleRepository roleRepository, IRoleEntityMapper roleEntityMapper){
        this.roleRepository = roleRepository;
        this.roleEntityMapper =roleEntityMapper;
    }

    @Override
    public void saveRole(Role role){
        roleRepository.save(roleEntityMapper.toEntity(role));
    }

}
