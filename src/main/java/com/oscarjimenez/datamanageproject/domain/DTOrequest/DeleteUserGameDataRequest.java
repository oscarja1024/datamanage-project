package com.oscarjimenez.datamanageproject.domain.DTOrequest;

public class DeleteUserGameDataRequest {

    String dataSource;
    String dataBase;
    String collection;

    Filter filter;

    public class Filter{
        String gameId;

        String userId;

        String deckId;
    }
}
