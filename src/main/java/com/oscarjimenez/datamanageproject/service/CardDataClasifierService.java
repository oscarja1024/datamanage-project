package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.service.DTO.ResultCardDTO;

import java.util.UUID;

public interface CardDataClasifierService {

    ResultCardDTO resultCardVsCard(String cardId1, String cardId2);

    boolean saveFavoriteCards(String cardID, UUID userId) throws Exception;
}
