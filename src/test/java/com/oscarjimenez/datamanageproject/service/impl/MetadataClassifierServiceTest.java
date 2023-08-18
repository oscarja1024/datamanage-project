package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.client.DTOS.MetadataResponseDTO;
import com.oscarjimenez.datamanageproject.client.FeignDataMinerConnection;
import com.oscarjimenez.datamanageproject.service.DTO.FilterDTO;
import com.oscarjimenez.datamanageproject.service.DTO.FilteredMetadataResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MetadataClassifierServiceTest {

    @InjectMocks
    private MetadataClasifierServiceImpl metadataClasifierService;

    @Mock
    private FeignDataMinerConnection feignDataMinerConnection;

    @BeforeEach
    public void setUp() {
        // Configuración de Mockito
    }

    @Test
    public void testFilterMetadata() {
        // Configurar el comportamiento esperado del mock FeignDataMinerConnection
        MetadataResponseDTO metadataResponseDTO = MetadataResponseDTO.builder().build();
        when(feignDataMinerConnection.getMetadata()).thenReturn(metadataResponseDTO);

        // Configurar los filtros
        FilterDTO filters = FilterDTO.builder().filterBy("sets").build();

        // Llamar al método y verificar el resultado
        FilteredMetadataResponseDTO result = metadataClasifierService.filterMetadata(filters);
        assertNotNull(result);

        // Verificar que se haya llamado al método del mock
        verify(feignDataMinerConnection, times(1)).getMetadata();
    }

    @Test
    public void testFilterMetadataWithException() {
        // Configurar el mock para lanzar una excepción simulada
        when(feignDataMinerConnection.getMetadata()).thenThrow(new RuntimeException("Simulated exception"));

        // Configurar los filtros
        FilterDTO filters = FilterDTO.builder().build();

        // Llamar al método y verificar que lanza la excepción
        assertThrows(RuntimeException.class, () -> metadataClasifierService.filterMetadata(filters));

        // Verificar que se haya llamado al método del mock
        verify(feignDataMinerConnection, times(1)).getMetadata();
    }
}
