package com.oscarjimenez.datamanageproject.client.DTOS;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@Jacksonized
@Builder
public class GetCardsResponseDTO {

    private List<GetOneCardResponseDTO> cards;
    private int cardCount;
    private int pageCount;
    private int page;

    public List<String> getCardIds(){

        List<String> result = new ArrayList<>();

        for(GetOneCardResponseDTO card : this.cards){
            if(!card.getId().isEmpty()){
                result.add(card.getId());
            }
        }

        return result;
    }

}
