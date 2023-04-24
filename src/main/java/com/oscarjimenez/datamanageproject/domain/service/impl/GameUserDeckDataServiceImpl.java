package com.oscarjimenez.datamanageproject.domain.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.FindGameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.domain.service.GameUserDeckDataService;
import com.oscarjimenez.datamanageproject.domain.utils.utilityDomainClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GameUserDeckDataServiceImpl implements GameUserDeckDataService {
    @Autowired
    private FeignMongodbConnection feignMongodbConnection;
    public InsertedId insertOwnedDeck(List<String> cardIds, String heroId,String userId,String deckId){
        return  feignMongodbConnection.insertGameUserData(utilityDomainClass.getApiKey(), GameUserDataRequest.builder()
                .userId(userId).heroId(heroId).cardsIds(cardIds).deckId(deckId).build());
    }

    public UserGameDataResponse getOwnedDeck(String deckId, String userId){
        var deck = feignMongodbConnection.findUserGameData(utilityDomainClass.getApiKey(), FindGameUserDataRequest.builder()
                .filter(FindGameUserDataRequest.Filter.builder().deckId(deckId).userId(userId).build()).build());
        return UserGameDataResponse.builder()
                .deckId(deck.getDeckId())
                .cardsIds(deck.getCardsIds()).build();
    }

    public InsertedId insertDeckReport(GameUserDataRequest gameUserDataRequest) {
        return feignMongodbConnection.insertGameUserData(utilityDomainClass.getApiKey(),gameUserDataRequest);
    }

    public UserGameDataResponse getDeckDataReport(String deckId, String userId){
        var deck = feignMongodbConnection.findUserGameData(utilityDomainClass.getApiKey(), FindGameUserDataRequest.builder()
                .filter(FindGameUserDataRequest.Filter.builder().deckId(deckId).userId(userId).build()).build());
        return UserGameDataResponse.builder()
                .deckId(deck.getDeckId())
                .cardsIds(deck.getCardsIds())
                .cardsInDeck(deck.getCardsInDeck())
                .minionsInDeck(deck.getMinionsInDeck())
                .secretsInDeck(deck.getSecretsInDeck())
                .spellsInDeck(deck.getSpellsInDeck())
                .cardsThatCreateOtherCards(deck.getCardsThatCreateOtherCards())
                .avarageAttack(deck.getAvarageAttack())
                .avarageHealth(deck.getAvarageHealth())
                .averageMana(deck.getAverageMana())
                .posibilityToHealACardFromAnotherCard(deck.getPosibilityToHealACardFromAnotherCard())
                .possibilityToStealCardFromAnotherCard(deck.getPossibilityToStealCardFromAnotherCard())
                .deckRate(deck.getDeckRate()).build();
    }



}
