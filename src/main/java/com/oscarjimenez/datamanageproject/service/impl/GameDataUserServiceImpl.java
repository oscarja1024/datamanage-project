package com.oscarjimenez.datamanageproject.service.impl;


import com.oscarjimenez.datamanageproject.domain.entity.GameEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.domain.repository.GameRepository;
import com.oscarjimenez.datamanageproject.domain.repository.UserRepository;
import com.oscarjimenez.datamanageproject.service.DTO.ResultGameDTO;
import com.oscarjimenez.datamanageproject.service.GameDataUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameDataUserServiceImpl implements GameDataUserService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public GameEntity saveGameReport(ResultGameDTO resultGameDTO, UUID userId) {

        var user = userRepository.findById(userId).get();

        var report = GameEntity.builder()
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
                .user(user)
                .build();

        return gameRepository.saveAndFlush(report);
    }

    @Override
    public void deleteGameReport(UUID gameId) {
        gameRepository.deleteById(gameId);
    }

    @Override
    public GameEntity getGameReportByGameIdAndUserId(UUID gameId, UserEntity userId) {
        return gameRepository.findByGameIdAndUser(gameId,userId);
    }
}
