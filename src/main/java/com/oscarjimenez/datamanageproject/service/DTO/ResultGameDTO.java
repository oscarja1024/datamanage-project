package com.oscarjimenez.datamanageproject.service.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class ResultGameDTO {


    private boolean result; //IF TRUE VICTORIA , IF FALSE DERROTA
    private String manaCostOfGame;
    private DamageDTO differentDamageInGame;
    private int numberOfRounds;
    private String cardsStolePerRound;
    private String cardsStoleInGame;
    private String healthHealToHero;
    private String healthHealInGame;
    private String heroHabilityUse;
    private String cardsStoleToOpponent;
    private String numberOfSpellsUsed;
    private UserAnnotationsDTO userAnnotationsDTO;
    private PuntuationDTO deckPuntuationUtility;

}
