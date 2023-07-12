package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.domain.service.GameUserGameDataService;
import com.oscarjimenez.datamanageproject.service.DTO.*;
import com.oscarjimenez.datamanageproject.service.GameDataUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameDataUserServiceImpl implements GameDataUserService {

    @Autowired
    GameUserGameDataService gameUserGameDataService;

    @Override
    public ResultGameDTO getGameReport(UUID gameId, UUID userId) {
        return gameUserGameDataService.getGameData(gameId,userId).getResultGame();
    }

    @Override
    public InsertedId saveGameReport(ResultGameDTO gameReport, UUID userId) {

        return gameUserGameDataService.saveGameData(GameUserDataRequest.builder().resultGame(gameReport).userId(userId).build());
    }

}
