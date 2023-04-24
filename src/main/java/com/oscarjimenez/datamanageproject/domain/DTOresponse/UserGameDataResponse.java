package com.oscarjimenez.datamanageproject.domain.DTOresponse;

import java.util.List;

public class UserGameDataResponse {


    String userId;
    String email;
    String cardId;
    List<String> cardsIds;
    String deckId;
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
}
