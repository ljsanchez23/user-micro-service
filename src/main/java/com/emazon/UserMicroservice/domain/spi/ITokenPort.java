package com.emazon.UserMicroservice.domain.spi;

public interface ITokenPort {
    String getToken(String email);
}
