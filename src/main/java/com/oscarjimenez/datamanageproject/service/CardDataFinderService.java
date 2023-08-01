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

    GetCardsResponseDTO getAllCardsSort(SortDTO sort, String page);

    GetCardsResponseDTO getAllCardsSetPageSize(String pageSize, String page);

    GetCardsResponseDTO getAllCardsByPageSetPageSizeSort(SortDTO sort, String pageSize, String page);

    GetCardsResponseDTO getAllCardsByManaCost(ManaDTO mana, String page);

    GetCardsResponseDTO getAllCardsByManaCostAndAttack(ManaDTO mana, AttackDTO attack, String page);

    GetCardsResponseDTO getAllCardsByAttack(AttackDTO attack, String page);

    GetCardsResponseDTO getAllCardsByType(String cardType, String page);

    GetCardsResponseDTO getAllCardsByTypeAndAttack(String cardType, AttackDTO attack, String page);

    GetCardsResponseDTO getAllCardsByTypeAndManaCost(String cardType,ManaDTO mana, String page);

    GetCardsResponseDTO getAllCardsByTypeAndAttackAndManaCost(String cardType,ManaDTO mana,AttackDTO attack, String page);

    GetCardsResponseDTO getAllCardsByHealth(HealthDTO health, String page);

    GetCardsResponseDTO getAllCardsByGameMode(String gameMode, String page);

    GetCardsResponseDTO getAllCardsBySpellSchool(String spellSchool, String page);

    GetCardsResponseDTO getAllCardsBySet(String cardSet, String page);
}
