package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.domain.repository.UserRepository;
import com.oscarjimenez.datamanageproject.service.DTO.UserDataDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDataServiceTest {

    @InjectMocks
    private UserDataServiceImpl userDataService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Configuración de Mockito
    }

    @Test
    public void testUserRegistration() {
        UserDataDTO userData = UserDataDTO.builder().build();
        userData.setUserId(UUID.randomUUID());
        userData.setEmail("test@example.com");
        userData.setName("Test User");
        userData.setPasswd("password");

        // Configurar el comportamiento esperado del mock UserRepository
        when(userRepository.saveAndFlush(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Llamar al método y verificar el resultado
        UserEntity result = userDataService.userRegistration(userData);
        assertEquals(userData.getEmail(), result.getEmail());
        assertEquals(userData.getName(), result.getName());
        assertEquals(Base64.getEncoder().encodeToString(userData.getPasswd().getBytes(StandardCharsets.UTF_8)), result.getPasswd());

        // Verificar que se haya llamado al método del mock
        verify(userRepository, times(1)).saveAndFlush(any(UserEntity.class));
    }

    @Test
    public void testGetUserData_UserExists() {
        UUID userId = UUID.randomUUID();
        UserEntity userEntity = new UserEntity();
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // Llamar al método y verificar el resultado
        UserEntity result = userDataService.getUserData(UserDataDTO.builder().userId(userId).build());
        assertEquals(userEntity, result);

        // Verificar que se haya llamado al método del mock
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetUserData_UserDoesNotExist() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Llamar al método y verificar el resultado
        UserEntity result = userDataService.getUserData(UserDataDTO.builder().userId(userId).build());
        assertNotNull(result);
        assertNull(result.getUserId());

        // Verificar que se haya llamado al método del mock
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testDeleteUser() {
        UUID userId = UUID.randomUUID();

        // Llamar al método
        userDataService.deleteUser(userId);

        // Verificar que se haya llamado al método del mock
        verify(userRepository, times(1)).deleteById(userId);
    }
}
