package com.oscarjimenez.datamanageproject.client.DTOS;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class SetDTO {

    private String id;
    private String name;
    private String type;
    private String collectibleCount;
    private String collectibleRevealedCount;
    private String nonCollectibleCount;
    private String nonCollectibleRevealedCount;

}
