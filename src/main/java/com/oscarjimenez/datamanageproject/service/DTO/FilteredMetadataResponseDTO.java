package com.oscarjimenez.datamanageproject.service.DTO;

import com.oscarjimenez.dataminerproject.client.DTOS.*;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class FilteredMetadataResponseDTO {

    private List<SetDTO> sets;
    private List<SetGroupsDTO> setGroups;
    private List<GameModeDTO> gameModes;
    private List<String> arenaIds;
    private List<TypeDTO> types;
    private List<RarityDTO> rarities;
    private List<ClassDTO> classes;
    private List<MinionTypeDTO> minionTypes;
    private List<SpellSchoolDTO> spellSchools;
    private List<MercenaryRoleDTO> mercenaryRoles;
    private List<MercenaryFactionDTO> mercenaryFactions;
    private List<KeyWordDTO> keywords;
    private List<String> filterableFields;
    private List<String> numericFields;
    private List<CardBackCategoryDTO> cardBackCategories;

}
