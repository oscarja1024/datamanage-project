package com.oscarjimenez.datamanageproject.domain.service;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;

import java.util.List;

public interface GameUserDeckDataService {

    InsertedId insertOwnedDeck(List<String> cardIds, String heroId, String userId, String deckId);

    UserGameDataResponse getOwnedDeck(String deckId, String userId);

    InsertedId insertDeckReport(GameUserDataRequest gameUserDataRequest);

    UserGameDataResponse getDeckDataReport(String deckId, String userId);
}
