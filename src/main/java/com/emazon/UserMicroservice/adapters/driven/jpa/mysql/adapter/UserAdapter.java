package com.emazon.UserMicroservice.adapters.driven.jpa.mysql.adapter;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.emazon.UserMicroservice.domain.model.User;
import com.emazon.UserMicroservice.domain.spi.IUserPersistencePort;


public class UserAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    public UserAdapter(IUserRepository userRepository, IUserEntityMapper userEntityMapper){
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public void createUser(User user){
        userRepository.save(userEntityMapper.toEntity(user));
    }
    @Override
    public boolean existsByEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }
    @Override
    public User findByEmail(String email){
        return userEntityMapper.toDomain(userRepository.findByEmail(email).get());
    }
}
