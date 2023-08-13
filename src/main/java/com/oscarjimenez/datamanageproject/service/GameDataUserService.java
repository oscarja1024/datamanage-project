package com.oscarjimenez.datamanageproject.service;


import com.oscarjimenez.datamanageproject.domain.entity.GameEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.service.DTO.ResultGameDTO;
import org.apache.catalina.User;

import java.util.UUID;

public interface GameDataUserService {

    GameEntity saveGameReport(ResultGameDTO resultGameDTO, UUID userId);

    void deleteGameReport(UUID gameId);

    GameEntity getGameReportByGameIdAndUserId(UUID gameId, UserEntity userId);

}
