package com.oscarjimenez.datamanageproject.client.DTOS;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class BearerTokenResponseDTO {

    private String access_token;
    private String token_type;
    private int expires_in;
    private String sub;

}
