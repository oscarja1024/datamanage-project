package com.oscarjimenez.datamanageproject.domain.service;

import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;

import java.util.UUID;


public interface GameUserCardDataService {


    InsertedId saveFavCard(UUID userId, String cardId);

    InsertedId saveResultCardVsCard(UUID userId, String cardId1, String cardId2, String winnerId);

    UserGameDataResponse getFavCards(UUID userId);

    DeletedCount deleteFavCard(UUID userId, String cardOd);
}
