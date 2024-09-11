package com.emazon.UserMicroservice.domain.api.usecase;

import com.emazon.UserMicroservice.domain.model.Role;
import com.emazon.UserMicroservice.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class RoleUseCaseTest {

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    public RoleUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should call saveRole on IRolePersistencePort when saveRole is called")
    void shouldCallSaveRoleOnRolePersistencePort() {
        Role role = new Role(1L, "Admin", "Administrator role");

        roleUseCase.saveRole(role);

        verify(rolePersistencePort).saveRole(role);
    }
}
