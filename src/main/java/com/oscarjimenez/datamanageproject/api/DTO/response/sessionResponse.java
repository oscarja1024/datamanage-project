package com.oscarjimenez.datamanageproject.api.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class sessionResponse {

    private UUID userId;

    private UUID sessionId;

    private String expiration;

}
