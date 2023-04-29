package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.service.DTO.ChangeId;
import com.oscarjimenez.datamanageproject.service.DTO.DeckReportDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import com.oscarjimenez.datamanageproject.service.DeckUserDataService;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;

public class DeckUseDataServiceImpl implements DeckUserDataService {
    @Override
    public DeckDTO findByUserIdandDeckId(String userId, String deckId) {
        return null;
    }

    @Override
    public boolean saveOwnedDeck(DeckDTO deckDTO) {
        return false;
    }

    @Override
    public ChangeId saveDeckPuntuation(PuntuationDTO puntuation, String deckId, String userId) {
        return null;
    }

    @Override
    public DeckReportDTO getDeckReport(String deckReportId, String deckId, String userId) {
        return null;
    }

    @Override
    public ChangeId generateDeckResport(String deckId, String userId) {
        return null;
    }
}
