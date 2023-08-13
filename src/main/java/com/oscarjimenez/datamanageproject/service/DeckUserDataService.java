package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.api.DTO.InsertDeckRequest;
import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.domain.entity.DeckEntity;
import com.oscarjimenez.datamanageproject.domain.entity.FavDeckEntity;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;

import java.util.UUID;

public interface DeckUserDataService {


    FavDeckEntity findByUserIdandDeckId(UUID userId, UUID deckId);

    FavDeckEntity saveOwnedDeck(InsertDeckRequest deckDTO, UUID userId);

    DeckEntity getDeckReport(UUID deckReportId);

    DeckEntity generateDeckResport(UUID deckId, UUID userId);

    void deleteDeckReport(UUID deckReportId);

    void deleteOwnedDeck(UUID ownedDeckId);


}
