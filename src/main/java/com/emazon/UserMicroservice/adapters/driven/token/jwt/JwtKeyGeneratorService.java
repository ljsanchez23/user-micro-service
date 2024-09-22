package com.emazon.UserMicroservice.adapters.driven.token.jwt;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.emazon.UserMicroservice.configuration.security.util.SecurityConstants;
import com.emazon.UserMicroservice.domain.model.User;
import com.emazon.UserMicroservice.domain.spi.ITokenPort;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class JwtKeyGeneratorService implements ITokenPort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    public JwtKeyGeneratorService(IUserRepository userRepository, IUserEntityMapper userEntityMapper){
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public String getToken(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(SecurityConstants.JWT_USER_NOT_FOUND + email));

        User user = userEntityMapper.toDomain(userEntity);
        String role = user.getRole().getName();
        Long userId = user.getId();

        return Jwts.builder()
                .id(SecurityConstants.JWT_ID)
                .subject(email)
                .claim(SecurityConstants.JWT_AUTHORITIES, Collections.singletonList(role))
                .claim(SecurityConstants.USER_ID, userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + SecurityConstants.JWT_TIME_EXPIRATION))
                .signWith(SecurityConstants.getSignedKey(SecurityConstants.JWT_SECRET_KEY))
                .compact();
    }
}
