package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.client.FeignDataMinerConnection;
import com.oscarjimenez.datamanageproject.client.DTOS.MetadataResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MetadataFinderServiceTest {
    @InjectMocks
    private MetadataFinderServiceImpl metadataFinderService;

    @Mock
    private FeignDataMinerConnection feignDataMinerConnection;

    @BeforeEach
    public void setUp() {
        // Configuración de Mockito
    }

    @Test
    public void testGetAllMetadata() {
        // Configurar el comportamiento esperado del mock FeignDataMinerConnection
        MetadataResponseDTO metadataResponseDTO = MetadataResponseDTO.builder().build();
        when(feignDataMinerConnection.getMetadata()).thenReturn(metadataResponseDTO);

        // Llamar al método y verificar el resultado
        MetadataResponseDTO result = metadataFinderService.getAllMetada();
        assertNotNull(result);

        // Verificar que se haya llamado al método del mock
        verify(feignDataMinerConnection, times(1)).getMetadata();
    }

    @Test
    public void testGetAllMetadata_NullResponse() {
        // Configurar el comportamiento esperado del mock FeignDataMinerConnection
        when(feignDataMinerConnection.getMetadata()).thenReturn(null);

        // Llamar al método y verificar el resultado
        MetadataResponseDTO result = metadataFinderService.getAllMetada();
        assertNull(result);

        // Verificar que se haya llamado al método del mock
        verify(feignDataMinerConnection, times(1)).getMetadata();
    }

    @Test
    public void testGetAllMetadata_FeignConnectionError() {
        // Configurar el comportamiento esperado del mock FeignDataMinerConnection
        when(feignDataMinerConnection.getMetadata()).thenThrow(new RuntimeException("Error"));

        // Llamar al método y verificar si se lanza la excepción esperada
        assertThrows(RuntimeException.class, () -> metadataFinderService.getAllMetada());

        // Verificar que se haya llamado al método del mock
        verify(feignDataMinerConnection, times(1)).getMetadata();
    }



}
