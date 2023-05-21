package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.domain.DTOrequest.FindGameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UpdateResponse;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.service.DTO.ChangeId;
import com.oscarjimenez.datamanageproject.service.DTO.DeckReportDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import com.oscarjimenez.datamanageproject.service.DeckUserDataService;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class DeckUseDataServiceImpl implements DeckUserDataService {

    @Autowired
    DeckFinderDataServiceImpl deckFinderDataService;

    @Autowired
    FeignMongodbConnection feignMongodbConnection;

    private String apiKey;

    @Override
    public DeckDTO findByUserIdandDeckId(String userId, String deckId) {

        var deckCardListByUser = feignMongodbConnection.findUserGameData(apiKey, FindGameUserDataRequest.builder().build());

        return deckFinderDataService.getDeckByCardListAndHero(deckCardListByUser.getCardsIds(),deckCardListByUser.getUserId());
    }

    @Override
    public boolean saveOwnedDeck(DeckDTO deckDTO) {

        var insertedId = feignMongodbConnection.insertGameUserData(apiKey, GameUserDataRequest.builder().build());

        return insertedId.toString().isEmpty() || insertedId.toString().isBlank();
    }

    @Override
    public UpdateResponse saveDeckPuntuation(PuntuationDTO puntuation, String deckId, String userId) {

        var updateResponse = feignMongodbConnection.updateUserGameData(apiKey, GameUserDataRequest.builder().build());

        return updateResponse;
    }

    @Override
    public DeckReportDTO getDeckReport(String deckReportId, String deckId, String userId) {

        var deckData = feignMongodbConnection.findUserGameData(apiKey, FindGameUserDataRequest.builder().build());

        return DeckReportDTO.builder().build();
    }

    @Override
    public boolean generateDeckResport(String deckId, String userId) {

        var ownedDeck = this.findByUserIdandDeckId(userId,deckId);

        var deckReport = DeckReportDTO.builder().build();

        var insertedID = feignMongodbConnection.insertGameUserData(apiKey, GameUserDataRequest.builder().build());

        return insertedID.toString().isEmpty() || insertedID.toString().isBlank();
    }
}
