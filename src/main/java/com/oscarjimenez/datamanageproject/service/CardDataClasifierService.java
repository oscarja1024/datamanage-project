package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.domain.entity.CardEntity;
import com.oscarjimenez.datamanageproject.service.DTO.ResultCardDTO;

import java.util.List;
import java.util.UUID;

public interface CardDataClasifierService {

    ResultCardDTO resultCardVsCard(String cardId1, String cardId2);

    CardEntity saveFavoriteCards(String cardID, UUID userId) throws Exception;

    void deleteFavoriteCards(String cardId, UUID userId);

    List<CardEntity> getFavoriteCardsByUser(UUID userId);
}
