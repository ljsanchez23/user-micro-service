package com.emazon.UserMicroservice.domain.api;

import com.emazon.UserMicroservice.domain.model.User;

public interface IUserServicePort {
    void createUser(User user);
}
