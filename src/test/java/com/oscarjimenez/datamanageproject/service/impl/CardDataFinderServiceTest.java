package com.oscarjimenez.datamanageproject.service.impl;
import com.oscarjimenez.datamanageproject.client.DTOS.GetCardsResponseDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.GetOneCardResponseDTO;
import com.oscarjimenez.datamanageproject.client.FeignDataMinerConnection;
import com.oscarjimenez.datamanageproject.service.DTO.AttackDTO;
import com.oscarjimenez.datamanageproject.service.DTO.HealthDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ManaDTO;
import com.oscarjimenez.datamanageproject.service.DTO.SortDTO;
import net.bytebuddy.TypeCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
public class CardDataFinderServiceTest {

    @InjectMocks
    private CardDataFinderServiceImpl cardDataFinderService;

    @Mock
    private FeignDataMinerConnection feignDataMinerConnection;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOneCardById() {
        // Create a mock response DTO
        GetOneCardResponseDTO mockResponse = GetOneCardResponseDTO.builder()
                .id("cardId1")
                .build();

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getOneCardById(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetOneCardResponseDTO result = cardDataFinderService.getOneCardById("cardId1");

        // Assert the result
        assertEquals("cardId1", result.getId());
    }

    @Test
    public void testGetAllCards() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();

        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCards(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCards("1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }

    @Test
    public void testGetAllCardsSort() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCardsSort(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsSort(SortDTO.builder().sort("asc").build(), "1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }

    @Test
    public void testGetAllCardsByManaCost() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCardsByManaCost(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsByManaCost( ManaDTO.builder().manaCost("3").build(), "1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }

    @Test
    public void testGetAllCardsByAttack() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCardsByAttack(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsByAttack(AttackDTO.builder().attack("10").build(), "1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }


    @Test
    public void testGetAllCardsByType() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCardsByType(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsByType("creature", "1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }

    @Test
    public void testGetAllCardsByTypeAndAttack() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCardsByTypeAndAttack(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsByTypeAndAttack("spell", AttackDTO.builder().attack("10").build(), "1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }

    @Test
    public void testGetAllCardsByTypeAndManaCost() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCardsByTypeAndManaCost(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsByTypeAndManaCost("spell", ManaDTO.builder().manaCost("5").build(), "1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }

    @Test
    public void testGetAllCardsByTypeAndAttackAndManaCost() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCardsByTypeAndAttackAndManaCost(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsByTypeAndAttackAndManaCost("spell", ManaDTO.builder().manaCost("3").build(),  AttackDTO.builder().attack("10").build(), "1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }

    @Test
    public void testGetAllCardsByHealth() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCardsByHealth(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsByHealth(HealthDTO.builder().health("7").build(), "1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }

    @Test
    public void testGetAllCardsByGameMode() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCardsByGameMode(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsByGameMode("standard", "1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }

    @Test
    public void testGetAllCardsBySpellSchool() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCardsBySpellSchool(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsBySpellSchool("fire", "1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }

    @Test
    public void testGetAllCardsBySet() {
        // Create a mock response DTO
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());

        // Mock FeignDataMinerConnection behavior
        when(feignDataMinerConnection.getAllCardsBySet(Mockito.any())).thenReturn(mockResponse);

        // Call the method under test
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsBySet("basic", "1");

        // Assert the result
        assertEquals(Collections.emptyList(), result.getCards());
    }


    @Test
    public void testGetAllCardsByManaCostAndAttack() {
        // Mock Feign client behavior
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());
        Mockito.when(feignDataMinerConnection.getAllCardsByManaCostAndAttack(Mockito.any()))
                .thenReturn(mockResponse);

        // Call the service method
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsByManaCostAndAttack(ManaDTO.builder().manaCost("5").build(), AttackDTO.builder().attack("5").build(), "1");

        assertEquals(Collections.emptyList(), result.getCards());

    }

    @Test
    public void testGetAllCardsSetPageSize() {
        // Mock Feign client behavior
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());
        Mockito.when(feignDataMinerConnection.getAllCardsSetPageSize(Mockito.any()))
                .thenReturn(mockResponse);

        // Call the service method
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsSetPageSize("10", "1");

        assertEquals(Collections.emptyList(), result.getCards());
    }

    @Test
    public void testGetAllCardsByPageSetPageSizeSort() {
        // Mock Feign client behavior
        GetCardsResponseDTO mockResponse = GetCardsResponseDTO.builder().build();
        mockResponse.setCards(Collections.emptyList());
        Mockito.when(feignDataMinerConnection.getAllCardsByPageSetPageSizeSort(Mockito.any()))
                .thenReturn(mockResponse);

        // Call the service method
        GetCardsResponseDTO result = cardDataFinderService.getAllCardsByPageSetPageSizeSort(SortDTO.builder().sort("asc").build(), "10", "1");

        assertEquals(Collections.emptyList(), result.getCards());
    }
}
