package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.service.DTO.SortDTO;
import com.oscarjimenez.dataminerproject.api.DTOS.CardRequestDTO;
import com.oscarjimenez.dataminerproject.api.DTOS.ControllerDTO.MinerDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetCardsResponseDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetOneCardResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CardDataFinderService {


    GetOneCardResponseDTO getOneCardById(String cardId);

    List<GetCardsResponseDTO> getAllCards();

    GetCardsResponseDTO getAllCardsSort(SortDTO sort);

    GetCardsResponseDTO getAllCardsSetPageSize(Object request);

    GetCardsResponseDTO getAllCardsByPageSetPageSizeSort(MinerDTO minerDTO);

    GetCardsResponseDTO getAllCardsByManaCost(MinerDTO minerDTO);

    GetCardsResponseDTO getAllCardsByManaCostAndAttack(MinerDTO minerDTO);

    GetCardsResponseDTO getAllCardsByAttack(MinerDTO minerDTO);

    GetCardsResponseDTO getAllCardsByType(MinerDTO minerDTO);

    GetCardsResponseDTO getAllCardsByTypeAndAttack(MinerDTO minerDTO);

    GetCardsResponseDTO getAllCardsByTypeAndManaCost(MinerDTO minerDTO);

    GetCardsResponseDTO getAllCardsByTypeAndAttackAndManaCost(MinerDTO minerDTO);

    GetCardsResponseDTO getAllCardsByHealth(MinerDTO minerDTO);

    GetCardsResponseDTO getAllCardsByGameMode(MinerDTO minerDTO);

    GetCardsResponseDTO getAllCardsBySpellSchool(MinerDTO minerDTO);

    GetCardsResponseDTO getAllCardsBySet(MinerDTO minerDTO);
}
