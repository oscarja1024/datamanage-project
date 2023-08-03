package com.oscarjimenez.datamanageproject.domain.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.DeleteUserGameDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.FindGameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.domain.service.GameUserCardDataService;
import com.oscarjimenez.datamanageproject.domain.utils.utilityDomainClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameUserCardDataServiceImpl implements GameUserCardDataService {
    @Autowired
    private FeignMongodbConnection feignMongodbConnection;

    @Value("{spring.data.mongodb.data-api.collection1}")
    private String collecton1;

    @Value("{spring.data.mongodb.data-api.collection2}")
    private String collecton2;

    @Value("{spring.data.mongodb.data-api.dataSource}")
    private String dataSource;

    @Value("{spring.data.mongodb.data-api.dataBase}")
    private String dataBase;


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

    @Override
    public DeletedCount deleteFavCard(UUID userId, String cardId){
        return feignMongodbConnection.deleteGameUserData(utilityDomainClass.getApiKey(), DeleteUserGameDataRequest.builder().filter(DeleteUserGameDataRequest.Filter.builder().userId(userId).cardId(cardId).build()).Build());;
    }
}
