package com.emazon.UserMicroservice.configuration;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.adapter.RoleAdapter;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.emazon.UserMicroservice.configuration.security.PasswordEncoder;
import com.emazon.UserMicroservice.domain.api.IRoleServicePort;
import com.emazon.UserMicroservice.domain.api.IUserServicePort;
import com.emazon.UserMicroservice.domain.api.usecase.RoleUseCase;
import com.emazon.UserMicroservice.domain.api.usecase.UserUseCase;
import com.emazon.UserMicroservice.domain.spi.IPasswordEncoder;
import com.emazon.UserMicroservice.domain.spi.IRolePersistencePort;
import com.emazon.UserMicroservice.domain.spi.IUserPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;
    private final IRoleRepository roleRepository;

    public BeanConfiguration(IUserRepository userRepository, IUserEntityMapper userEntityMapper, IRoleEntityMapper roleEntityMapper, IRoleRepository roleRepository){
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.roleEntityMapper = roleEntityMapper;
        this.roleRepository = roleRepository;
    }
    @Bean
    public IPasswordEncoder passwordEncoder() {
        return new PasswordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), passwordEncoder());
    }

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }
}
