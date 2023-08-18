package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.api.DTO.response.sessionResponse;
import com.oscarjimenez.datamanageproject.domain.entity.SessionEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.domain.repository.SessionRepository;
import com.oscarjimenez.datamanageproject.domain.repository.UserRepository;
import com.oscarjimenez.datamanageproject.service.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class sessionServiceImplTest {

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Configuración de Mockito
    }

    @Test
    public void testGetSession_ValidCredentials() {
        // Configurar el comportamiento esperado del mock UserRepository
        String email = "test@example.com";
        String password = "password";
        UserEntity user = new UserEntity();
        when(userRepository.findByEmailAndPasswd(email, password)).thenReturn(user);

        // Configurar el comportamiento esperado del mock SessionRepository
        SessionEntity sessionEntity = new SessionEntity();
        UUID sessionId = UUID.randomUUID();
        sessionEntity.setSessionId(sessionId);
        when(sessionRepository.saveAndFlush(any(SessionEntity.class))).thenReturn(sessionEntity);

        // Llamar al método y verificar el resultado
        sessionResponse result = sessionService.getSession(email, password);
        assertNotNull(result.getSessionId());
        assertEquals(user.getUserId(), result.getUserId());

        // Verificar que se haya llamado a los métodos de los mocks
        verify(userRepository, times(1)).findByEmailAndPasswd(email, password);
        verify(sessionRepository, times(1)).saveAndFlush(any(SessionEntity.class));
    }

    @Test
    public void testGetSession_InvalidCredentials() {
        // Configurar el comportamiento esperado del mock UserRepository
        String email = "test@example.com";
        String password = "wrongpassword";
        when(userRepository.findByEmailAndPasswd(email, password)).thenReturn(null);

        // Llamar al método y verificar el resultado
        sessionResponse result = sessionService.getSession(email, password);
        assertNull(result.getSessionId());
        assertNull(result.getUserId());

        // Verificar que se haya llamado a los métodos de los mocks
        verify(userRepository, times(1)).findByEmailAndPasswd(email, password);
        verify(sessionRepository, times(0)).saveAndFlush(any(SessionEntity.class));
    }

    @Test
    public void testDeleteSession() {
        UUID sessionId = UUID.randomUUID();

        // Llamar al método
        sessionService.deleteSession(sessionId);

        // Verificar que se haya llamado al método del mock
        verify(sessionRepository, times(1)).deleteById(sessionId);
    }

    @Test
    public void testSessionVerify_ValidSession() {
        UUID sessionId = UUID.randomUUID();
        UserEntity user = new UserEntity();

        // Configurar el comportamiento esperado del mock SessionRepository
        when(sessionRepository.findByUserAndSessionId(user, sessionId)).thenReturn(new SessionEntity());

        // Llamar al método y verificar el resultado
        boolean result = sessionService.sessionVerify(sessionId, user);
        assertTrue(result);

        // Verificar que se haya llamado al método del mock
        verify(sessionRepository, times(1)).findByUserAndSessionId(user, sessionId);
    }

    @Test
    public void testSessionVerify_InvalidSession() {
        UUID sessionId = UUID.randomUUID();
        UserEntity user = new UserEntity();

        // Configurar el comportamiento esperado del mock SessionRepository
        when(sessionRepository.findByUserAndSessionId(user, sessionId)).thenReturn(null);

        // Llamar al método y verificar el resultado
        boolean result = sessionService.sessionVerify(sessionId, user);
        assertFalse(result);

        // Verificar que se haya llamado al método del mock
        verify(sessionRepository, times(1)).findByUserAndSessionId(user, sessionId);
    }
}
