package com.oscarjimenez.datamanageproject.client.DTOS;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class CardBacksDTO {

    public String id;
    private String sortCategory;
    private String text;
    private String name;
    private String image;
    private String slug;

}
