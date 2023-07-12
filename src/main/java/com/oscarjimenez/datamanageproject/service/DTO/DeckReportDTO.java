package com.oscarjimenez.datamanageproject.service.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Jacksonized
@Builder
public class DeckReportDTO {

    private UUID deckId;
    private String manaMean;
    private String attackMean;
    private PuntuationDTO puntuationDTO;
    private String spellsMean;
    private String healthMean;
    private String minionCount;
    private String cardCount;
    private String posibilityOfOpponentCardStole;
    private String posibilityOfExtraCardStole;
    private String healMean;
    private String secretsCount;
    private String cardCreation;
    private String attackIncrease;

}
