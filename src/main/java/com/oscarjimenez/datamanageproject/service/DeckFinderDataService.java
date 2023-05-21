package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.dataminerproject.api.DTOS.ControllerDTO.MinerDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;

import java.util.List;

public interface DeckFinderDataService {

    DeckDTO getDeckByCardListAndHero(List<String> cardIds, String heroId);

    DeckDTO getDeckByCardListAutoHero(List<String> cardIds);

    DeckDTO getDeckByCode(String code);

}
