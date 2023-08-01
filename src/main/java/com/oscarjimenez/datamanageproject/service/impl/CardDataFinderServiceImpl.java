package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.client.FeignDataMinerConnection;
import com.oscarjimenez.datamanageproject.service.CardDataFinderService;
import com.oscarjimenez.datamanageproject.service.DTO.AttackDTO;
import com.oscarjimenez.datamanageproject.service.DTO.HealthDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ManaDTO;
import com.oscarjimenez.datamanageproject.service.DTO.SortDTO;
import com.oscarjimenez.dataminerproject.api.DTOS.ControllerDTO.MinerDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetCardsResponseDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetOneCardResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.core.io.NumberInput.parseInt;

@Service
public class CardDataFinderServiceImpl implements CardDataFinderService {

    @Autowired
    FeignDataMinerConnection feignDataMinerConnection;

    @Override
    public GetOneCardResponseDTO getOneCardById(String cardId) {

        var request = MinerDTO.builder().cardId(cardId).page("0").build();

        return feignDataMinerConnection.getOneCardById(request);
    }

    @Override
    public GetCardsResponseDTO getAllCards(String page) {

        List<GetCardsResponseDTO> result = new ArrayList<>();

        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).build());

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsSort(SortDTO sort, String page) {

        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("SORT",sort.getSort());
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).build());
        result.add(resultado);




        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsSetPageSize(String pageSize,String page) {

        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("PAGE_SIZE",pageSize);

        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).params(params).build());
        result.add(resultado);
        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsByPageSetPageSizeSort(SortDTO sort, String pageSize, String page) {

        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("SORT",sort.getSort());
        params.put("PAGE_SIZE",pageSize);
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).params(params).build());
        result.add(resultado);

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsByManaCost(ManaDTO mana, String page) {

        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("MANA", mana.getManaCost());
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).build());
        result.add(resultado);

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsByManaCostAndAttack(ManaDTO mana, AttackDTO attack, String page) {

        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("MANA", mana.getManaCost());
        params.put("ATTACK", attack.getAttack());
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).build());
        result.add(resultado);

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsByAttack(AttackDTO attack, String page) {
        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("ATTACK", attack.getAttack());
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).build());
        result.add(resultado);

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsByType(String cardType, String page) {
        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("CARD_TYPE",cardType);
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).params(params).build());
        result.add(resultado);

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsByTypeAndAttack(String cardType, AttackDTO attack, String page) {
        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("CARD_TYPE",cardType);
        params.put("ATTACK", attack.getAttack());
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).params(params).build());
        result.add(resultado);

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsByTypeAndManaCost(String cardType,ManaDTO mana, String page) {
        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("CARD_TYPE",cardType);
        params.put("MANA", mana.getManaCost());
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).params(params).build());
        result.add(resultado);

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsByTypeAndAttackAndManaCost(String cardType,ManaDTO mana,AttackDTO attack, String page) {
        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("CARD_TYPE",cardType);
        params.put("MANA", mana.getManaCost());
        params.put("ATTACK", attack.getAttack());
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).params(params).build());
        result.add(resultado);

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsByHealth(HealthDTO health, String page) {
        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("HEALTH",health.getHealth());
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).params(params).build());
        result.add(resultado);

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsByGameMode(String gameMode, String page) {
        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("GAME_MODE",gameMode);
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).params(params).build());
        result.add(resultado);

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsBySpellSchool(String spellSchool, String page) {
        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("SPELL_SCHOOL",spellSchool);
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).params(params).build());
        result.add(resultado);

        return resultado;
    }

    @Override
    public GetCardsResponseDTO getAllCardsBySet(String cardSet, String page) {
        List<GetCardsResponseDTO> result = new ArrayList<>();

        Map<String,String> params = new HashMap<>();
        params.put("CARD_SET",cardSet);
        var resultado = feignDataMinerConnection.getAllCards(MinerDTO.builder().page(page).params(params).build());
        result.add(resultado);

        return resultado;
    }
}
