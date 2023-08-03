package com.oscarjimenez.datamanageproject.client.DTOS;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class KeyWordDTO {

    private String id;
    private String slug;
    private String refText;
    private String text;
    private List<String> gameModes;
}
