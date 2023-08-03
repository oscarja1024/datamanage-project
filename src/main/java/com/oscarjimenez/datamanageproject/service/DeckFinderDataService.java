package com.oscarjimenez.datamanageproject.service;


import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;

import java.util.List;

public interface DeckFinderDataService {

    DeckDTO getDeckByCardListAndHero(List<String> cardIds, String heroId);

    DeckDTO getDeckByCardListAutoHero(List<String> cardIds);

    DeckDTO getDeckByCode(String code);

}
