package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.domain.DTOresponse.UpdateResponse;
import com.oscarjimenez.datamanageproject.service.DTO.ChangeId;
import com.oscarjimenez.datamanageproject.service.DTO.DeckReportDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;

import java.util.UUID;

public interface DeckUserDataService {


    DeckDTO findByUserIdandDeckId(UUID userId, String deckId);

    boolean saveOwnedDeck(DeckDTO deckDTO);

    UpdateResponse saveDeckPuntuation(PuntuationDTO puntuation, String deckId, String userId);

    DeckReportDTO getDeckReport(String deckReportId, String deckId, String userId);

    boolean generateDeckResport(String deckId, UUID userId);

}
