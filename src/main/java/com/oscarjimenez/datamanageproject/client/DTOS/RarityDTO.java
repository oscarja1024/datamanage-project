package com.oscarjimenez.datamanageproject.client.DTOS;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class RarityDTO {

    private String slug;
    private String id;
    private List<String> craftingCost;
    private List<String> dustValue;
    private String name;
}
