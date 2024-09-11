package com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.UserMicroservice.domain.model.User;

public interface IUserEntityMapper {
    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);
}
