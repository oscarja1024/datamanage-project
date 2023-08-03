package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.api.utils.constants;
import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.GetOneCardResponseDTO;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.FindGameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOrequest.GameUserDataRequest;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.DeletedCount;
import com.oscarjimenez.datamanageproject.domain.DTOresponse.UpdateResponse;
import com.oscarjimenez.datamanageproject.domain.client.FeignMongodbConnection;
import com.oscarjimenez.datamanageproject.service.DTO.DeckReportDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import com.oscarjimenez.datamanageproject.service.DeckUserDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class DeckUseDataServiceImpl implements DeckUserDataService {

    @Autowired
    DeckFinderDataServiceImpl deckFinderDataService;

    @Autowired
    FeignMongodbConnection feignMongodbConnection;

    private String apiKey;

    @Override
    public DeckDTO findByUserIdandDeckId(UUID userId, UUID deckId) {

        var deckCardListByUser = feignMongodbConnection.findUserGameData(apiKey, FindGameUserDataRequest.builder().build());

        return deckFinderDataService.getDeckByCardListAndHero(deckCardListByUser.getCards().getCardIds(), "");
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

    public PuntuationDTO getDeckPuntuation(UUID deckId, UUID userId) {

        var deckData = feignMongodbConnection.findUserGameDataOne(apiKey, FindGameUserDataRequest.builder().filter(FindGameUserDataRequest.Filter.builder().deckId(deckId).userId(userId).build()).build());

        return deckData.getPuntuation();
    }

    @Override
    public boolean generateDeckResport(UUID deckId, UUID userId) {

        var ownedDeck = this.findByUserIdandDeckId(userId,deckId);

        var deckReport = DeckReportDTO.builder()
                .deckId(ownedDeck.getDeckCode())
                .cardCount(String.valueOf(ownedDeck.getCardCount()))
                .attackMean(this.calculateMean(ownedDeck.getCards(), constants.ATTACK))
                .manaMean(this.calculateMean(ownedDeck.getCards(), constants.MANA))
                .healthMean(this.calculateMean(ownedDeck.getCards(), constants.HEALTH))
                .spellsMean(this.calculateMean(ownedDeck.getCards(), constants.SPELLS))
                .minionCount(this.calculateMean(ownedDeck.getCards(), constants.MINION))
                .puntuationDTO(this.getDeckPuntuation(ownedDeck.getDeckCode(), userId))
                .secretsCount(this.calculateMean(ownedDeck.getCards(), constants.SECRET))
                .attackIncrease(this.calculateMean(ownedDeck.getCards(), constants.INCRESE))
                .healMean(this.calculateMean(ownedDeck.getCards(), constants.HEAL))
                .build(); // Se puede mejorar recorriendo solo una vez la lista

        var insertedID = feignMongodbConnection.insertGameUserData(apiKey, (List<GameUserDataRequest>) GameUserDataRequest.builder().document(GameUserDataRequest.Document.builder().userId(userId).deckId(deckId).build()));

        return insertedID.toString().isEmpty() || insertedID.toString().isBlank();
    }

    private String calculateMean(List<GetOneCardResponseDTO> cards, String value){

        String result = "";

        double mean = 0;

        for(GetOneCardResponseDTO card : cards) {
            int number = 0;
            switch (value){
                case constants.ATTACK:
                    number = Integer.parseInt(card.getAttack());
                    break;
                case constants.MANA:
                    number = Integer.parseInt(card.getManaCost());
                    break;
                case constants.HEALTH:
                    number = Integer.parseInt(card.getHealth());
                    break;
                case constants.SPELLS:
                case constants.SECRET:
                case constants.MINION:
                    number = this.whatIs(card);
                    mean = mean + number;
                    return String.valueOf(mean);
                case constants.INCRESE:
                    number = this.increaseAttack(card.getText());
                    mean = mean + number;
                case constants.HEAL:
                    number = this.increaseHeal(card.getText());

            }
            mean = mean + number;
        }

        result = String.valueOf(mean/cards.size());

        return result;

    }

    private int whatIs(GetOneCardResponseDTO card){

        int result = 0;

        switch (card.getCardTypeId()){
            case "5":
            case "3":
            case "4" :
                result = 1;
                break;

        }

        return result;
    }

    private int increaseAttack(String text){

        int result = 0;

        if(text.contains("Attack") && text.contains("+") ){
            result = 1;
        }

        return result;

    }

    private int increaseHeal(String text){

        int result = 0;

        if(text.contains("Health") && (text.contains("give") || text.contains("gain") || text.contains("restore"))){
            result = 1;
        }

        return result;

    }
}


