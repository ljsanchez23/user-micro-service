package com.emazon.UserMicroservice.domain.spi;

import com.emazon.UserMicroservice.domain.model.Role;

public interface IRolePersistencePort {
    void saveRole(Role role);

}
