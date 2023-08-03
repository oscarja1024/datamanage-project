package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.client.FeignDataMinerConnection;
import com.oscarjimenez.datamanageproject.service.DeckFinderDataService;
import com.oscarjimenez.datamanageproject.client.DTOS.MinerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class DeckFinderDataServiceImpl implements DeckFinderDataService {

    @Autowired
    FeignDataMinerConnection feignDataMinerConnection;

    @Override
    public DeckDTO getDeckByCardListAndHero(List<String> cardIds, String heroId) {

        var result = feignDataMinerConnection.getDeckByCardListAndHero(MinerDTO.builder().cardIds(cardIds).cardId(heroId).page("0").build());

        return result;
    }

    @Override
    public DeckDTO getDeckByCardListAutoHero(List<String> cardIds) {
        var result = feignDataMinerConnection.getDeckByCardListAndHero(MinerDTO.builder().cardIds(cardIds).page("0").build());

        return result;
    }

    @Override
    public DeckDTO getDeckByCode(String code) {

        var params = Map.of("CODE", code);

        var result = feignDataMinerConnection.getDeckByCode(MinerDTO.builder().params(params).page("0").build());

        return result;
    }
}
