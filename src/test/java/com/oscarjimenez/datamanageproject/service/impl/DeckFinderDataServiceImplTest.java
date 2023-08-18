package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.client.FeignDataMinerConnection;
import com.oscarjimenez.datamanageproject.client.DTOS.MinerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class DeckFinderDataServiceImplTest {

    @InjectMocks
    private DeckFinderDataServiceImpl deckFinderDataService;

    @Mock
    private FeignDataMinerConnection feignDataMinerConnection;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDeckByCardListAndHero() {
        // Create a mock response DTO
        DeckDTO mockResponse = DeckDTO.builder()
                .build();

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getDeckByCardListAndHero(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        DeckDTO result = deckFinderDataService.getDeckByCardListAndHero("cardIds", "heroId");

        // Assert the result
        assertNotNull(result);
    }

    @Test
    public void testGetDeckByCardListAutoHero() {
        // Create a mock response DTO
        DeckDTO mockResponse = DeckDTO.builder()
                .build();

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getDeckByCardListAutoHero(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        DeckDTO result = deckFinderDataService.getDeckByCardListAutoHero("cardIds");

        // Assert the result
        assertNotNull(result);
    }

    @Test
    public void testGetDeckByCode() {
        // Create a mock response DTO
        DeckDTO mockResponse = DeckDTO.builder()
                .build();

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getDeckByCode(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        DeckDTO result = deckFinderDataService.getDeckByCode("testCode");

        // Assert the result
        assertNotNull(result);
    }

    // End of tests
}

