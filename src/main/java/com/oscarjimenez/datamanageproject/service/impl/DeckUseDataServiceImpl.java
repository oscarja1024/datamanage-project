package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.api.DTO.InsertDeckRequest;
import com.oscarjimenez.datamanageproject.api.utils.constants;
import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.GetOneCardResponseDTO;
import com.oscarjimenez.datamanageproject.domain.entity.DeckEntity;
import com.oscarjimenez.datamanageproject.domain.entity.FavDeckEntity;
import com.oscarjimenez.datamanageproject.domain.repository.DeckRepository;
import com.oscarjimenez.datamanageproject.domain.repository.FavDeckRepository;
import com.oscarjimenez.datamanageproject.service.DTO.DeckReportDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import com.oscarjimenez.datamanageproject.service.DeckUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeckUseDataServiceImpl implements DeckUserDataService {

    @Autowired
    DeckFinderDataServiceImpl deckFinderDataService;

    @Autowired
    FavDeckRepository favdeckRepository;

    @Autowired
    DeckRepository deckRepository;

    @Override
    public FavDeckEntity findByUserIdandDeckId(UUID userId, UUID deckId) {

        var response = favdeckRepository.findByDeckIdAndUserId(userId,deckId);
        return response;
    }

    @Override
    public FavDeckEntity saveOwnedDeck(InsertDeckRequest deckDTO, UUID userId) {
        return favdeckRepository.saveAndFlush(FavDeckEntity.builder().cardIds(deckDTO.getCardIds()).heroId(deckDTO.getHeroId()).userId(userId).build());
    }

    @Override
    public DeckEntity getDeckReport(UUID deckReportId) {

        var response = deckRepository.findById(deckReportId);

        return response.orElseGet(() -> DeckEntity.builder().build());

    }

    @Override
    public DeckEntity generateDeckResport(UUID deckId, UUID userId) {

        var ownedDeck = this.findByUserIdandDeckId(userId,deckId);

        DeckDTO deck = deckFinderDataService.getDeckByCardListAndHero(ownedDeck.getCardIds(),ownedDeck.getHeroId());

        var deckReport = DeckReportDTO.builder()
                .deckId(deckId)
                .cardCount(String.valueOf(deck.getCardCount()))
                .attackMean(this.calculateMean(deck.getCards(), constants.ATTACK))
                .manaMean(this.calculateMean(deck.getCards(), constants.MANA))
                .healthMean(this.calculateMean(deck.getCards(), constants.HEALTH))
                .spellsMean(this.calculateMean(deck.getCards(), constants.SPELLS))
                .minionCount(this.calculateMean(deck.getCards(), constants.MINION))
                .secretsCount(this.calculateMean(deck.getCards(), constants.SECRET))
                .attackIncrease(this.calculateMean(deck.getCards(), constants.INCRESE))
                .healMean(this.calculateMean(deck.getCards(), constants.HEAL))
                .build(); // Se puede mejorar recorriendo solo una vez la lista

        var entity = deckRepository.saveAndFlush(DeckEntity.builder().build());

        return entity;
    }

    @Override
    public void deleteDeckReport(UUID deckReportId) {
        deckRepository.deleteById(deckReportId);
    }

    @Override
    public void deleteOwnedDeck(UUID ownedDeckId) {
        favdeckRepository.deleteById(ownedDeckId);
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


