package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.domain.DTOresponse.UpdateResponse;
import com.oscarjimenez.datamanageproject.service.DTO.ChangeId;
import com.oscarjimenez.datamanageproject.service.DTO.DeckReportDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;
public interface DeckUserDataService {


    DeckDTO findByUserIdandDeckId(String userId, String deckId);

    boolean saveOwnedDeck(DeckDTO deckDTO);

    UpdateResponse saveDeckPuntuation(PuntuationDTO puntuation, String deckId, String userId);

    DeckReportDTO getDeckReport(String deckReportId, String deckId, String userId);

    boolean generateDeckResport(String deckId, String userId);

}
