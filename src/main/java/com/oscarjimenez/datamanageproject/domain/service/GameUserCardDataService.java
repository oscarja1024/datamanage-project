package com.oscarjimenez.datamanageproject.domain.service;

import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;

public interface GameUserCardDataService {


    InsertedId saveFavCard(String userId, String cardId);

    InsertedId saveResultCardVsCard(String userId, String cardId1, String cardId2, String winnerId);

    UserGameDataResponse getFavCards(String userId);
}
