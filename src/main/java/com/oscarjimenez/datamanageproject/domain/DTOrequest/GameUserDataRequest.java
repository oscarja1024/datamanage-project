package com.oscarjimenez.datamanageproject.domain.DTOrequest;

import com.oscarjimenez.datamanageproject.service.DTO.DeckReportDTO;
import com.oscarjimenez.datamanageproject.service.DTO.PuntuationDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ResultGameDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.DeckDTO;
import com.oscarjimenez.dataminerproject.client.DTOS.GetOneCardResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class GameUserDataRequest {


    UUID userId;
    DeckDTO favDeck;
    GetOneCardResponseDTO favCard;
    DeckReportDTO deckReport;
    PuntuationDTO puntuation;
    ResultGameDTO resultGame;
    String cardId;
    String cardId2;
    String winnerCardVsCard;

}
