package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.domain.DTOresponse.InsertedId;
import com.oscarjimenez.datamanageproject.service.DTO.*;

import java.util.UUID;

public interface GameDataUserService {


    ResultGameDTO getGameReport(UUID gameId, UUID userId);

    InsertedId saveGameReport(ResultGameDTO gameReport, UUID userId);

}
