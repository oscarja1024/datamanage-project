package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.service.GameUserGameDataService;
import com.oscarjimenez.datamanageproject.service.DTO.*;
import com.oscarjimenez.datamanageproject.service.GameDataUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameDataUserServiceImpl implements GameDataUserService {

    @Autowired
    GameUserGameDataService gameUserGameDataService;

    @Value("${spring.data.mongodb.dataSource}")
    private static String dataSource;

    @Value("${spring.data.mongodb.database}")
    private static String dataBase;

    @Value("${spring.data.mongodb.collection2}")
    private static String collection;


    @Override
    public ResultGameDTO getGameReport(UUID gameId, UUID userId) {
        return gameUserGameDataService.getGameData(gameId,userId).getResultGame();
    }

    @Override
    public InsertedId saveGameReport(ResultGameDTO gameReport, UUID userId) {

        return gameUserGameDataService.saveGameData(GameUserDataRequest.builder().dataBase(dataBase).dataSource(dataSource).collection(collection).document(GameUserDataRequest.Document.builder().resultGame(gameReport).userId(userId).build()).build());
    }

    @Override
    public DeletedCount deleteGameReport(UUID gameId, UUID userId){
        return gameUserGameDataService.deleteGameData(gameId,userId);
    }

}
