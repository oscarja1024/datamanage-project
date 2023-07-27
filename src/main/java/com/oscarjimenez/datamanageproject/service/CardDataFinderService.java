package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.service.DTO.AttackDTO;
import com.oscarjimenez.datamanageproject.service.DTO.HealthDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ManaDTO;
import com.oscarjimenez.datamanageproject.service.DTO.SortDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetCardsResponseDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetOneCardResponseDTO;

public interface CardDataFinderService {


    GetOneCardResponseDTO getOneCardById(String cardId);

    GetCardsResponseDTO getAllCards(String page);

    GetCardsResponseDTO getAllCardsSort(SortDTO sort);

    GetCardsResponseDTO getAllCardsSetPageSize(String pageSize);

    GetCardsResponseDTO getAllCardsByPageSetPageSizeSort(SortDTO sort, String pageSize);

    GetCardsResponseDTO getAllCardsByManaCost(ManaDTO mana);

    GetCardsResponseDTO getAllCardsByManaCostAndAttack(ManaDTO mana, AttackDTO attack);

    GetCardsResponseDTO getAllCardsByAttack(AttackDTO attack);

    GetCardsResponseDTO getAllCardsByType(String cardType);

    GetCardsResponseDTO getAllCardsByTypeAndAttack(String cardType, AttackDTO attack);

    GetCardsResponseDTO getAllCardsByTypeAndManaCost(String cardType,ManaDTO mana);

    GetCardsResponseDTO getAllCardsByTypeAndAttackAndManaCost(String cardType,ManaDTO mana,AttackDTO attack);

    GetCardsResponseDTO getAllCardsByHealth(HealthDTO health);

    GetCardsResponseDTO getAllCardsByGameMode(String gameMode);

    GetCardsResponseDTO getAllCardsBySpellSchool(String spellSchool);

    GetCardsResponseDTO getAllCardsBySet(String cardSet);
}
