package com.emazon.UserMicroservice.domain.spi;

public interface IEncoderPort {
    String encode(String password);
    boolean matches(String rawPassword, String encodedPassword);
}
