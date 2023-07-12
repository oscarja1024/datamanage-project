package com.oscarjimenez.datamanageproject.domain.DTOrequest;

import java.util.UUID;

public class DeleteUserGameDataRequest {

    String dataSource;
    String dataBase;
    String collection;

    Filter filter;

    public class Filter{
        UUID gameId;

        UUID userId;

        UUID deckId;
    }
}
