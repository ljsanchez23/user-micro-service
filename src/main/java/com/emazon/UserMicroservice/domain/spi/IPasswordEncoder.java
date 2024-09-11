package com.emazon.UserMicroservice.domain.spi;

public interface IPasswordEncoder {
    String encode(String password);
}
