package com.oscarjimenez.datamanageproject.client.DTOS;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Set;

@Data
@Jacksonized
@Builder
public class HeroDTO {

    private String id;
    private String collectible;
    private String slug;
    private String classId;
    private Set<Object> multiClassIds;
    private String spellSchoolId;
    private String cardTypeId;
    private String cardSetId;
    private String rarityId;
    private String artistName;
    private String manaCost;
    private String name;
    private String image;
    private String imageGold;
    private String flavorText;
    private String cropImage;
    private String parentId;
    private List<String> childIds;

}
