package com.emazon.UserMicroservice.domain.api;

import com.emazon.UserMicroservice.domain.model.Role;

public interface IRoleServicePort {
    void saveRole(Role role);
}
