package com.emazon.UserMicroservice.adapters.driven.jpa.mysql.adapter;

import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.UserMicroservice.domain.model.Role;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.emazon.UserMicroservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


class RoleAdapterTest {

    private IRoleRepository roleRepository;
    private IRoleEntityMapper roleEntityMapper;
    private RoleAdapter roleAdapter;

    @BeforeEach
    void setUp() {
        roleRepository = mock(IRoleRepository.class);
        roleEntityMapper = mock(IRoleEntityMapper.class);
        roleAdapter = new RoleAdapter(roleRepository, roleEntityMapper);
    }

    @Test
    @DisplayName("Should save the role")
    void shouldSaveRole() {

        Role role = new Role(1L, "Admin", "Admin role");
        RoleEntity roleEntity = new RoleEntity();
        when(roleEntityMapper.toEntity(role)).thenReturn(roleEntity);

        roleAdapter.saveRole(role);

        verify(roleEntityMapper).toEntity(role);
        verify(roleRepository).save(roleEntity);
    }
}
