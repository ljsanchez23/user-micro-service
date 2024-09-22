package com.emazon.UserMicroservice.adapters.driven.token.util;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.emazon.UserMicroservice.configuration.security.util.SecurityConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserRepository userRepository;

    public UserDetailsServiceImpl(IUserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(SecurityConstants.THE_USER + email + SecurityConstants.DOES_NOT_EXISTS));

        GrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole().getName());
        Collection<GrantedAuthority> authorities = Collections.singletonList(authority);

        return new User(userEntity.getEmail(), userEntity.getPassword(),
                        true, true, true, true,
                authorities);
    }
}
