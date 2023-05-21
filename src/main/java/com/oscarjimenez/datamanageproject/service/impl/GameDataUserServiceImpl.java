package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.service.DTO.*;
import com.oscarjimenez.datamanageproject.service.GameDataUserService;

public class GameDataUserServiceImpl implements GameDataUserService {
    @Override
    public ChangeId saveTotalDamageToHero(DamageDTO damageToHero) {
        return null;
    }

    @Override
    public ChangeId saveTotalDamageToMinions(DamageDTO damageToMinions) {
        return null;
    }

    @Override
    public ChangeId saveManaCostOfGame(ManaDTO manaCost) {
        return null;
    }

    @Override
    public ChangeId saveLostWinOfGame(ResultGameDTO result) {
        return null;
    }

    @Override
    public ChangeId generateNewGame(String userId) {
        return null;
    }

    @Override
    public ResultGameDTO getGameReport(String gameId, String userId) {
        return null;
    }

    @Override
    public ChangeId saveGameReport(ResultGameDTO gameReport, String userId) {
        return null;
    }

    @Override
    public ChangeId saveUserAnnotations(UserAnnotationsDTO userAnnotationsDTO) {
        return null;
    }
}
