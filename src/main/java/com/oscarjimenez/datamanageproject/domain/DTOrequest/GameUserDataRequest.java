package com.oscarjimenez.datamanageproject.domain.DTOrequest;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class GameUserDataRequest {


    String userId;
    String email;
    String cardId;
    List<String> cardsIds;
    String deckId;
    String heroId;
    int wins;
    int lost;
    double averageMana;
    double avarageAttack;
    double avarageHealth;
    double totalAttackToHeroes;
    double totalAttackToMinions;
    String userAnnotations;
    int deckRate;
    String gameId;
    int openentDeckSteal;
    int possibilityToStealCardFromAnotherCard;
    int posibilityToHealACardFromAnotherCard;
    int secretsInDeck;
    int spellsInDeck;
    int minionsInDeck;
    int cardsInDeck;
    int cardsThatCreateOtherCards;
    String cardId2;
    String winnerCardVsCard;

}
