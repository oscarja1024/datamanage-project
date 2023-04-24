package com.oscarjimenez.datamanageproject.domain.DTOrequest;

public class DeleteUserDataRequest {

    String dataSource;
    String dataBase;
    String collection;

    Filter filter;

    public class Filter{
        String userId;

    }
}
