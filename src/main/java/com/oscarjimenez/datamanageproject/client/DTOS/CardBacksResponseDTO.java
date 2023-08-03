package com.oscarjimenez.datamanageproject.client.DTOS;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class CardBacksResponseDTO {

    private List<CardBacksDTO> cardBacks;
    private int cardCount;
    private int pageCount;
    private int page;

}
