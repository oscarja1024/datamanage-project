package com.oscarjimenez.datamanageproject.service.impl;


import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.HeroDTO;
import com.oscarjimenez.datamanageproject.domain.entity.GameEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.domain.repository.GameRepository;
import com.oscarjimenez.datamanageproject.domain.repository.UserRepository;
import com.oscarjimenez.datamanageproject.service.DTO.DamageDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ResultGameDTO;
import com.oscarjimenez.datamanageproject.service.DTO.UserAnnotationsDTO;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameDataUserServiceTest {

    @InjectMocks
    private GameDataUserServiceImpl gameDataUserService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private UserRepository userRepository;


    @Test
    public void testSaveGameReport() {
        UUID userId = UUID.randomUUID();
        ResultGameDTO resultGameDTO = ResultGameDTO.builder().cardsStoleInGame("")
                .cardsStolePerRound("")
                .cardsStoleToOpponent("")
                .differentDamageInGame(DamageDTO.builder().build())
                .deckUsedInGame(DeckDTO.builder().hero(HeroDTO.builder().id("").name("").build()).heroPower(HeroDTO.builder().name("").build()).build())
                .result(false)
                .healthHealInGame("")
                .manaCostOfGame("")
                .healthHealToHero("")
                .deckPuntuationUtility(PuntuationDTO.builder().build())
                .numberOfRounds(1)
                .userAnnotationsDTO(UserAnnotationsDTO.builder()
                        .build())
                .heroHabilityUse("")
                .numberOfSpellsUsed("")
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(UserEntity.builder().userId(userId).build()));

        GameEntity savedGameEntity = GameEntity.builder()
                .cardsStoleInGame(resultGameDTO.getCardsStoleInGame())
                .cardCount(resultGameDTO.getDeckUsedInGame().getCardCount())
                .cardIds(resultGameDTO.getDeckUsedInGame().getCardsIds())
                .cardsStolePerRound(resultGameDTO.getCardsStolePerRound())
                .cardsStoleToOpponent(resultGameDTO.getCardsStoleToOpponent())
                .damageToHero(resultGameDTO.getDifferentDamageInGame().getDamageToHero())
                .DamageToHeroPerRound(resultGameDTO.getDifferentDamageInGame().getDamageToHeroPerRound())
                .damageToMinions(resultGameDTO.getDifferentDamageInGame().getDamageToMinions())
                .DamageToMinionsXRound(resultGameDTO.getDifferentDamageInGame().getDamageToMinionsXRound())
                .deckUsedInGame(resultGameDTO.getDeckUsedInGame().getOwnedGameId())
                .result(resultGameDTO.isResult())
                .healthHealInGame(resultGameDTO.getHealthHealInGame())
                .manaCostOfGame(resultGameDTO.getManaCostOfGame())
                .healthHealToHero(resultGameDTO.getHealthHealToHero())
                .deckCode(resultGameDTO.getDeckUsedInGame().getDeckCode())
                .deckPuntuationUtility(resultGameDTO.getDeckPuntuationUtility().toString())
                .heroId(resultGameDTO.getDeckUsedInGame().getHero().getId())
                .numberOfRounds(resultGameDTO.getNumberOfRounds())
                .heroPower(resultGameDTO.getDeckUsedInGame().getHeroPower().getName())
                .userAnnotationsDTO(resultGameDTO.getUserAnnotationsDTO().getUserAnnotations())
                .heroHabilityUse(resultGameDTO.getHeroHabilityUse())
                .numberOfSpellsUsed(resultGameDTO.getNumberOfSpellsUsed())
                .user(UserEntity.builder().userId(userId).build())
                .manaCostOfGame(resultGameDTO.getManaCostOfGame())
                .build();
        when(gameRepository.saveAndFlush(savedGameEntity)).thenReturn(savedGameEntity);

        GameEntity result = gameDataUserService.saveGameReport(resultGameDTO, userId);

        assertEquals(savedGameEntity, result);

        verify(userRepository, times(1)).findById(userId);
        verify(gameRepository, times(1)).saveAndFlush(any(GameEntity.class));
    }

    @Test
    public void testDeleteGameReport() {
        UUID gameId = UUID.randomUUID();

        gameDataUserService.deleteGameReport(gameId);

        verify(gameRepository, times(1)).deleteById(gameId);
    }

    @Test
    public void testGetGameReportByGameIdAndUserId() {
        UUID gameId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UserEntity userEntity = UserEntity.builder().userId(userId).build();
        GameEntity mockGameEntity = GameEntity.builder().build();

        when(gameRepository.findByGameIdAndUser(gameId, userEntity)).thenReturn(mockGameEntity);

        GameEntity result = gameDataUserService.getGameReportByGameIdAndUserId(gameId, UserEntity.builder().userId(userId).build());

        assertEquals(mockGameEntity, result);

    }

    @Test
    public void testFindGameReportsByUserId() {
        UUID userId = UUID.randomUUID();
        UserEntity userEntity = UserEntity.builder().userId(userId).build();
        List<GameEntity> mockGameEntities = Collections.singletonList(GameEntity.builder().build());

        when(gameRepository.findByUser(userEntity)).thenReturn(mockGameEntities);

        List<GameEntity> result = gameDataUserService.findGameReportsByUserId(userEntity);

        assertEquals(mockGameEntities, result);

    }











}
