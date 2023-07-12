package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.domain.service.GameUserCardDataService;
import com.oscarjimenez.datamanageproject.service.CardDataClasifierService;
import com.oscarjimenez.datamanageproject.service.DTO.ResultCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.lang.Integer.parseInt;
@Service
public class CardDataClasifierServiceImpl implements CardDataClasifierService {


    @Autowired
    CardDataFinderServiceImpl cardDataFinderService;

    @Autowired
    GameUserCardDataService gameUserCardDataService;

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
    public boolean saveFavoriteCards(String cardID, UUID userId) throws Exception {

        try {
            gameUserCardDataService.saveFavCard(userId,cardID);
        } catch (Exception e){
            throw new Exception("Por un problema interno no se ha podido guardar la carta de manera satisfactoriaa",e);
        }

        return true;
    }
}
