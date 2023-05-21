package com.oscarjimenez.datamanageproject.service.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class FilterDTO {

    private String filterBy; //Valid values include sets, setGroups, types, rarities, classes, minionTypes, and keywords.
}