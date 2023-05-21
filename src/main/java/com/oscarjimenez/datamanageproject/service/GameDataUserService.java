package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.service.DTO.*;

public interface GameDataUserService {

    ChangeId saveTotalDamageToHero(DamageDTO damageToHero);

    ChangeId saveTotalDamageToMinions(DamageDTO damageToMinions);

    ChangeId saveManaCostOfGame(ManaDTO manaCost);

    ChangeId saveLostWinOfGame(ResultGameDTO result);

    ChangeId generateNewGame(String userId);

    ResultGameDTO getGameReport(String gameId, String userId);

    ChangeId saveGameReport(ResultGameDTO gameReport, String userId);

    ChangeId saveUserAnnotations(UserAnnotationsDTO userAnnotationsDTO);
}
