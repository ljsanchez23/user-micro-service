package com.emazon.UserMicroservice.domain.spi;

import com.emazon.UserMicroservice.domain.model.User;

public interface IUserPersistencePort {
    void createUser(User user);
    boolean existsByEmail(String email);
}
