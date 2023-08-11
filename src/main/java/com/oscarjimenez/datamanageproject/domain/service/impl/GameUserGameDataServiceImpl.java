package com.oscarjimenez.datamanageproject.domain.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.DeleteUserGameDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.FindGameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.domain.service.GameUserGameDataService;
import com.oscarjimenez.datamanageproject.domain.utils.utilityDomainClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameUserGameDataServiceImpl implements GameUserGameDataService {

    @Autowired
    private FeignMongodbConnection feignMongodbConnection;

    @Value("${spring.data.mongodb.dataSource}")
    private static String dataSource;

    @Value("${spring.data.mongodb.database}")
    private static String dataBase;

    @Value("${spring.data.mongodb.collection2}")
    private static String collection;

    public InsertedId saveGameData(GameUserDataRequest gameUserDataRequest){
        return feignMongodbConnection.insertGameUserData(utilityDomainClass.getApiKey(), gameUserDataRequest);
    }

    public UserGameDataResponse getGameData(UUID gameId, UUID userId){
        var gameReport = feignMongodbConnection.findUserGameData(utilityDomainClass.getApiKey()
                , FindGameUserDataRequest.builder().dataBase(dataBase).dataSource(dataSource).collection(collection).filter(FindGameUserDataRequest.Filter.builder().gameId(gameId).userId(userId).build()).build());
        return UserGameDataResponse.builder()
                .resultGame(gameReport.getResultGame())
                .build();
    }

    @Override
    public DeletedCount deleteGameData(UUID gameId, UUID userId){
        return feignMongodbConnection.deleteGameUserData(utilityDomainClass.getApiKey(), DeleteUserGameDataRequest.builder().dataBase(dataBase).dataSource(dataSource).collection(collection).filter(DeleteUserGameDataRequest.Filter.builder().userId(userId).gameId(gameId).build()).build());
    }
}
