package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UpdateResponse;
import com.oscarjimenez.datamanageproject.service.DTO.DeckReportDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;

import java.util.UUID;

public interface DeckUserDataService {


    DeckDTO findByUserIdandDeckId(UUID userId, UUID deckId);

    boolean saveOwnedDeck(DeckDTO deckDTO);

    UpdateResponse saveDeckPuntuation(PuntuationDTO puntuation, String deckId, String userId);

    DeckReportDTO getDeckReport(String deckReportId, String deckId, String userId);

    boolean generateDeckResport(UUID deckId, UUID userId);

    DeletedCount deleteDeckReport();

    DeletedCount deleteOwnedDeck();
}
