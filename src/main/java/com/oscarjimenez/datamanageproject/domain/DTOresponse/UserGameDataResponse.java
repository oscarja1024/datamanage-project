package com.oscarjimenez.datamanageproject.domain.DTOresponse;

import com.oscarjimenez.datamanageproject.service.DTO.DeckReportDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ResultGameDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetCardsResponseDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetOneCardResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
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
