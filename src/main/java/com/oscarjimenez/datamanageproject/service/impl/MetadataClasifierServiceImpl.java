package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.client.FeignDataMinerConnection;
import com.oscarjimenez.datamanageproject.service.DTO.FilterDTO;
import com.oscarjimenez.datamanageproject.service.DTO.FilteredMetadataResponseDTO;
import com.oscarjimenez.datamanageproject.service.MetadataClasifierService;
import com.oscarjimenez.dataminerproject.client.DTOS.MetadataResponseDTO;
import feign.FeignException;
import feign.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetadataClasifierServiceImpl implements MetadataClasifierService {

    @Autowired
    FeignDataMinerConnection feignDataMinerConnection;

    @Override
    public FilteredMetadataResponseDTO filterMetadata(FilterDTO filters) {

        var metadata = feignDataMinerConnection.getMetadata();

        try {
            return filteredData(metadata, filters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private FilteredMetadataResponseDTO filteredData(MetadataResponseDTO metadata, FilterDTO filters) throws Exception {

        var result = FilteredMetadataResponseDTO.builder().build();

        switch (filters.getFilterBy()){
            case "sets":
                result.setSets(metadata.getSets());
                break;
            case "setGroups":
                result.setSetGroups(metadata.getSetGroups());
                break;
            case "gameModes":
                result.setGameModes(metadata.getGameModes());
                break;
            case "arenaIds" :
                result.setArenaIds(metadata.getArenaIds());
                break;
            case "types":
                result.setTypes(metadata.getTypes());
                break;
            case "rarities" :
                result.setRarities(metadata.getRarities());
                break;
            case "classes" :
                result.setClasses(metadata.getClasses());
                break;
            case "minionTypes" :
                result.setMinionTypes(metadata.getMinionTypes());
                break;
            case "spellSchools" :
                result.setSpellSchools(metadata.getSpellSchools());
                break;
            case "mercenaryRoles" :
                result.setMercenaryRoles(metadata.getMercenaryRoles());
                break;
            case "mercenaryFactions" :
                result.setMercenaryFactions(metadata.getMercenaryFactions());
                break;
            case "keywords":
                result.setKeywords(metadata.getKeywords());
                break;
            case "filterableFields" :
                result.setFilterableFields(metadata.getFilterableFields());
                break;
            case "numericFields" :
                result.setNumericFields(metadata.getNumericFields());
                break;
            case "cardBackCategories" :
                result.setCardBackCategories(metadata.getCardBackCategories());
                break;
            default:
                throw new Exception("Not a filter type"+filters.getFilterBy());
        }


        return  result;
    }
}
