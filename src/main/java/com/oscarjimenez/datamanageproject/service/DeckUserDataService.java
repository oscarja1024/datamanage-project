package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.api.DTO.InsertDeckRequest;
import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.domain.entity.DeckEntity;
import com.oscarjimenez.datamanageproject.domain.entity.FavDeckEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;

import java.util.UUID;

public interface DeckUserDataService {


    FavDeckEntity findByUserIdandDeckId(UUID deckId, UserEntity userId);

    FavDeckEntity saveOwnedDeck(InsertDeckRequest deckDTO, UUID userId);

    DeckEntity getDeckReport(UUID deckReportId);

    DeckEntity generateDeckResport(UserEntity userId, UUID deckId);

    void deleteDeckReport(UUID deckReportId);

    void deleteOwnedDeck(UUID ownedDeckId);



}
