package com.oscarjimenez.datamanageproject.domain.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.FindGameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.domain.service.GameUserDeckDataService;
import com.oscarjimenez.datamanageproject.domain.utils.utilityDomainClass;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.HeroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameUserDeckDataServiceImpl implements GameUserDeckDataService {
    @Autowired
    private FeignMongodbConnection feignMongodbConnection;
    public InsertedId insertOwnedDeck(List<String> cardIds, String heroId,UUID userId,UUID deckId){
        return  feignMongodbConnection.insertGameUserData(utilityDomainClass.getApiKey(), GameUserDataRequest.builder()
                .userId(userId).favDeck(DeckDTO.builder().hero(HeroDTO.builder().id(heroId).build()).cardsIds(cardIds).build()).build());
    }

    public UserGameDataResponse getOwnedDeck(UUID deckId, UUID userId){
        var deck = feignMongodbConnection.findUserGameData(utilityDomainClass.getApiKey(), FindGameUserDataRequest.builder()
                .filter(FindGameUserDataRequest.Filter.builder().deckId(deckId).userId(userId).build()).build());
        return UserGameDataResponse.builder()
                .favDeck(deck.getFavDeck()).build();
    }

    public InsertedId insertDeckReport(GameUserDataRequest gameUserDataRequest) {
        return feignMongodbConnection.insertGameUserData(utilityDomainClass.getApiKey(),gameUserDataRequest);
    }

    public UserGameDataResponse getDeckDataReport(UUID deckId, UUID userId){
        var deck = feignMongodbConnection.findUserGameData(utilityDomainClass.getApiKey(), FindGameUserDataRequest.builder()
                .filter(FindGameUserDataRequest.Filter.builder().deckId(deckId).userId(userId).build()).build());
        return UserGameDataResponse.builder()
                .deckReport(deck.getDeckReport()).build();
    }



}
