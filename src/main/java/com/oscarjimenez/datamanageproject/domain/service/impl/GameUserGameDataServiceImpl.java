package com.oscarjimenez.datamanageproject.domain.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.FindGameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.domain.service.GameUserGameDataService;
import com.oscarjimenez.datamanageproject.domain.utils.utilityDomainClass;
import org.springframework.beans.factory.annotation.Autowired;

public class GameUserGameDataServiceImpl implements GameUserGameDataService {

    @Autowired
    private FeignMongodbConnection feignMongodbConnection;

    public InsertedId saveGameData(GameUserDataRequest gameUserDataRequest){
        return feignMongodbConnection.insertGameUserData(utilityDomainClass.getApiKey(), gameUserDataRequest);
    }

    public UserGameDataResponse getGameData(String gameId, String userId){
        var gameReport = feignMongodbConnection.findUserGameData(utilityDomainClass.getApiKey()
                , FindGameUserDataRequest.builder().filter(FindGameUserDataRequest.Filter.builder().gameId(gameId).userId(userId).build()).build());
        return UserGameDataResponse.builder()
                .gameId(gameReport.getGameId())
                .deckRate(gameReport.getDeckRate())
                .openentDeckSteal(gameReport.getOpenentDeckSteal())
                .userAnnotations(gameReport.getUserAnnotations())
                .wins(gameReport.getWins())
                .lost(gameReport.getLost())
                .totalAttackToMinions(gameReport.getTotalAttackToMinions())
                .totalAttackToHeroes(gameReport.getTotalAttackToHeroes())
                .build();
    }
}
