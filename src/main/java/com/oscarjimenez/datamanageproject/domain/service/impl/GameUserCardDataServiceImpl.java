package com.oscarjimenez.datamanageproject.domain.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.FindGameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.domain.service.GameUserCardDataService;
import com.oscarjimenez.datamanageproject.domain.utils.utilityDomainClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameUserCardDataServiceImpl implements GameUserCardDataService {
    @Autowired
    private FeignMongodbConnection feignMongodbConnection;

    public InsertedId saveFavCard(UUID userId, String cardId){
        return feignMongodbConnection.insertGameUserData(utilityDomainClass.getApiKey()
                , GameUserDataRequest.builder().userId(userId).cardId(cardId).build());
    }

    public InsertedId saveResultCardVsCard(UUID userId, String cardId1, String cardId2, String winnerId){
        return  feignMongodbConnection.insertGameUserData(utilityDomainClass.getApiKey()
                , GameUserDataRequest.builder().userId(userId).cardId(cardId1).cardId2(cardId2).winnerCardVsCard(winnerId).build());
    }

    public UserGameDataResponse getFavCards(UUID userId){
        var userFavCards = feignMongodbConnection.findUserGameData(utilityDomainClass.getApiKey()
                , FindGameUserDataRequest.builder().
                        filter(FindGameUserDataRequest.Filter.builder().userId(userId).build())
                        .build());
        return UserGameDataResponse.builder().cards((userFavCards
                .getCards())).userId(userId).build();
    }
}
