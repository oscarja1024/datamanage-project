package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.client.DTOS.GetOneCardResponseDTO;
import com.oscarjimenez.datamanageproject.domain.entity.CardEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.domain.repository.CardRepository;
import com.oscarjimenez.datamanageproject.domain.repository.UserRepository;
import com.oscarjimenez.datamanageproject.service.DTO.ResultCardDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CardDataClasifierServiceTest {

    @InjectMocks
    private CardDataClasifierServiceImpl cardDataClasifierService;

    @Mock
    private CardDataFinderServiceImpl cardDataFinderService;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testResultCardVsCard() {
        GetOneCardResponseDTO card1 = GetOneCardResponseDTO.builder().id("cardId1").attack("10").build();
        GetOneCardResponseDTO card2 = GetOneCardResponseDTO.builder().id("cardId2").attack("2").build();
        // Define los comportamientos simulados
        when(cardDataFinderService.getOneCardById("cardId1")).thenReturn(card1);
        when(cardDataFinderService.getOneCardById("cardId2")).thenReturn(card2);

        // Llama al método bajo prueba
        ResultCardDTO result = cardDataClasifierService.resultCardVsCard("cardId1", "cardId2");

        // Verifica el resultado esperado
        assertEquals("cardId1", result.getWinnerResult());
    }

    @Test
    public void testSaveFavoriteCards() {
        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        CardEntity card = new CardEntity();
        when(cardRepository.saveAndFlush(any(CardEntity.class))).thenReturn(card);

        CardEntity result = cardDataClasifierService.saveFavoriteCards("cardID", userId);

        assertNotNull(result);
        verify(cardRepository).saveAndFlush(any(CardEntity.class));
    }

}
