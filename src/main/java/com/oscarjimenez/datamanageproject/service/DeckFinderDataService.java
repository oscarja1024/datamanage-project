package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.dataminerproject.api.DTOS.ControllerDTO.MinerDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;

public interface DeckFinderDataService {

    DeckDTO getDeckByCardListAndHero(MinerDTO minerDTO);

    DeckDTO getDeckByCardListAutoHero(MinerDTO minerDTO);

    DeckDTO getDeckByCode(MinerDTO minerDTO);

}
