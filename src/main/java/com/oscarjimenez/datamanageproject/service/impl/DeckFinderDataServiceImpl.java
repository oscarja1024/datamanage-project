package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.client.FeignDataMinerConnection;
import com.oscarjimenez.datamanageproject.service.DeckFinderDataService;
import com.oscarjimenez.datamanageproject.client.DTOS.MinerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DeckFinderDataServiceImpl implements DeckFinderDataService {

    @Autowired
    FeignDataMinerConnection feignDataMinerConnection;

    @Override
    public DeckDTO getDeckByCardListAndHero(String cardIds, String heroId) {

        Map<String, String> params = new HashMap<>();
        params.put("HERO", heroId);
        var result = feignDataMinerConnection.getDeckByCardListAndHero(MinerDTO.builder().cardIds(cardIds).params(params).page("0").build());

        return result;
    }

    @Override
    public DeckDTO getDeckByCardListAutoHero(String cardIds) {
        var result = feignDataMinerConnection.getDeckByCardListAutoHero(MinerDTO.builder().cardIds(cardIds).page("0").build());

        return result;
    }

    @Override
    public DeckDTO getDeckByCode(String code) {

        var params = Map.of("CODE", code);

        var result = feignDataMinerConnection.getDeckByCode(MinerDTO.builder().params(params).page("0").build());

        return result;
    }
}
