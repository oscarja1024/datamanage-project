package com.oscarjimenez.datamanageproject.domain.DTOrequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindGameUserDataRequest {
    String dataSource;
    String dataBase;
    String collection;

    Filter filter;

    @Data
    @Builder
    public class Filter{
        String userId;

        String deckId;

        String cardId;

        String gameId;
    }
}
