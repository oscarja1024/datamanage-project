package com.oscarjimenez.datamanageproject.service.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class DamageDTO {

    private String damageToHero;
    private String damageToMinions;
    private String DamageToHeroPerRound;
    private String DamageToMinionsXRound;

}
