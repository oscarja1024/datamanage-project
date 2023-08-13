package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.domain.entity.CardEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.domain.repository.CardRepository;
import com.oscarjimenez.datamanageproject.domain.repository.UserRepository;
import com.oscarjimenez.datamanageproject.service.CardDataClasifierService;
import com.oscarjimenez.datamanageproject.service.DTO.ResultCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.Integer.parseInt;
@Service
public class CardDataClasifierServiceImpl implements CardDataClasifierService {

    @Autowired
    CardDataFinderServiceImpl cardDataFinderService;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResultCardDTO resultCardVsCard(String cardId1, String cardId2) {

        var card1 = cardDataFinderService.getOneCardById(cardId1);
        var card2 = cardDataFinderService.getOneCardById(cardId2);
        var result = ResultCardDTO.builder().build();

        if(parseInt(card1.getAttack()) < parseInt(card2.getAttack())){

            result.setWinnerResult(cardId2);

        } else if (parseInt(card1.getAttack()) > parseInt(card2.getAttack())){

            result.setWinnerResult(cardId1);

        } else {

            result.setWinnerResult("0");

        }


        return result;
    }

    @Override
    public CardEntity saveFavoriteCards(String cardID, UUID userId) {

       return cardRepository.saveAndFlush(CardEntity.builder().idorSlug(cardID).user(UserEntity.builder().userId(userId).build()).build());
    }

    @Override
    public void deleteFavoriteCards(String cardId, UUID userId) {
        cardRepository.delete(CardEntity.builder().idorSlug(cardId).user(UserEntity.builder().userId(userId).build()).build());
    }

    @Override
    public List<CardEntity> getFavoriteCardsByUser(UUID userId) {
        return userRepository.findCardsByUserId(userId);
    }
}
