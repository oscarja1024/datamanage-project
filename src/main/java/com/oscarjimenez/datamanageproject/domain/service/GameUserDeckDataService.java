package com.oscarjimenez.datamanageproject.domain.service;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UserGameDataResponse;

import java.util.List;
import java.util.UUID;

public interface GameUserDeckDataService {

    InsertedId insertOwnedDeck(List<String> cardIds, String heroId, UUID userId, UUID deckId);

    UserGameDataResponse getOwnedDeck(UUID deckId, UUID userId);

    InsertedId insertDeckReport(GameUserDataRequest gameUserDataRequest);

    UserGameDataResponse getDeckDataReport(UUID deckId, UUID userId);

    DeletedCount deleteDeckDataReport(UUID deckId, UUID deckReportId, UUID userId);

    DeletedCount deleteOwnedDeck(UUID deckId, UUID userId);

}
