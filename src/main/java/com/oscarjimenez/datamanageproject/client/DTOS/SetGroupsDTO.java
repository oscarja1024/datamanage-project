package com.oscarjimenez.datamanageproject.client.DTOS;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class SetGroupsDTO {

    private String slug;
    private String year;
    private String svg;
    private List<String> cardSets;
    private String name;
    private boolean standard;
    private String icon;
}
