package com.oscarjimenez.datamanageproject.domain.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.FindGameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.domain.service.GameUserGameDataService;
import com.oscarjimenez.datamanageproject.domain.utils.utilityDomainClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameUserGameDataServiceImpl implements GameUserGameDataService {

    @Autowired
    private FeignMongodbConnection feignMongodbConnection;

    public InsertedId saveGameData(GameUserDataRequest gameUserDataRequest){
        return feignMongodbConnection.insertGameUserData(utilityDomainClass.getApiKey(), gameUserDataRequest);
    }

    public UserGameDataResponse getGameData(UUID gameId, UUID userId){
        var gameReport = feignMongodbConnection.findUserGameData(utilityDomainClass.getApiKey()
                , FindGameUserDataRequest.builder().filter(FindGameUserDataRequest.Filter.builder().gameId(gameId).userId(userId).build()).build());
        return UserGameDataResponse.builder()
                .resultGame(gameReport.getResultGame())
                .build();
    }
}
