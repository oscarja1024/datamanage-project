package com.oscarjimenez.datamanageproject.domain.service;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;

import java.util.UUID;

public interface GameUserGameDataService {

    InsertedId saveGameData(GameUserDataRequest gameUserDataRequest);

    UserGameDataResponse getGameData(UUID gameId, UUID userId);

    DeletedCount deleteGameData(UUID gameId, UUID userId);
}
