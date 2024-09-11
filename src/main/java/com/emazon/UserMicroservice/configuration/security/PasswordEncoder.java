package com.emazon.UserMicroservice.configuration.security;

import com.emazon.UserMicroservice.domain.spi.IPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder implements IPasswordEncoder {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

}
