package com.oscarjimenez.datamanageproject.domain.DTOrequest;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DeleteUserGameDataRequest {

    String dataSource;
    String dataBase;
    String collection;

    Filter filter;

    @Data
    @Builder
    public static class Filter{
        UUID gameId;

        UUID userId;

        UUID deckId;

        String cardId;
    }
}
