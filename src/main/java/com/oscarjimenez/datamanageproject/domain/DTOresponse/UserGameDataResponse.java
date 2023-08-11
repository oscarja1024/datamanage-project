package com.oscarjimenez.datamanageproject.domain.DTOresponse;

import com.oscarjimenez.datamanageproject.service.DTO.DeckReportDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ResultGameDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.GetOneCardResponseDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.GetCardsResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserGameDataResponse {


    UUID userId;
    DeckDTO favDeck;
    GetOneCardResponseDTO favCard;
    GetCardsResponseDTO cards;
    DeckReportDTO deckReport;
    PuntuationDTO puntuation;
    ResultGameDTO resultGame;

}
